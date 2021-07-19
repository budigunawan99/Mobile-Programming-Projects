/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.bnawan.saferoute.tflite;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Trace;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.task.vision.detector.Detection;
import org.tensorflow.lite.task.vision.detector.ObjectDetector;
import org.tensorflow.lite.task.vision.detector.ObjectDetector.ObjectDetectorOptions;

/**
 * Wrapper for frozen detection models trained using the Tensorflow Object Detection API: -
 * https://github.com/tensorflow/models/tree/master/research/object_detection where you can find the
 * training code.
 *
 * <p>To use pretrained models in the API or convert to TF Lite models, please see docs for details:
 * -
 * https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/tf1_detection_zoo.md
 * -
 * https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/tf2_detection_zoo.md
 * -
 * https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/running_on_mobile_tensorflowlite.md#running-our-model-on-android
 *
 * <p>For more information about Metadata and associated fields (eg: `labels.txt`), see <a
 * href="https://www.tensorflow.org/lite/convert/metadata#read_the_metadata_from_models">Read the
 * metadata from models</a>
 */
public class TFLiteObjectDetectionAPIModel implements Detector {
    private static final String TAG = "TFLiteObjectDetectionAPIModelWithTaskApi";

    /**
     * Only return this many results.
     */
    private static final int NUM_DETECTIONS = 10;

    /**
     * An instance of the driver class to run model inference with Tensorflow Lite.
     */
    private final ObjectDetector objectDetector;

    private Map<Integer, String> objek = createObjek();

    /**
     * Initializes a native TensorFlow session for classifying images.
     *
     * <p>{@code labelFilename}, {@code inputSize}, and {@code isQuantized}, are NOT required, but to
     * keep consistency with the implementation using the TFLite Interpreter Java API. See <a
     * href="https://github.com/tensorflow/examples/blob/master/lite/examples/object_detection/android/lib_interpreter/src/main/java/org/tensorflow/lite/examples/detection/tflite/TFLiteObjectDetectionAPIModel.java">lib_interpreter</a>.
     *
     * @param modelFilename The model file path relative to the assets folder
     * @param labelFilename The label file path relative to the assets folder
     * @param inputSize     The size of image input
     * @param isQuantized   Boolean representing model is quantized or not
     */

    public static Detector create(
            final Context context,
            final String modelFilename,
            final String labelFilename,
            final int inputSize,
            final boolean isQuantized)
            throws IOException {
        return new TFLiteObjectDetectionAPIModel(context, modelFilename);
    }

    private TFLiteObjectDetectionAPIModel(Context context, String modelFilename) throws IOException {
        List<String> allowedList = Arrays.asList(
                "person", "fire hydrant", "stop sign", "parking meter", "bench", "backpack", "umbrella",
                "suitcase", "sports ball", "banana", "chair", "couch", "potted plant", "bed", "dining table",
                "tv", "microwave", "oven", "toaster", "sink", "refrigerator", "clock", "vase"
        );
        ObjectDetectorOptions options =
                ObjectDetectorOptions.builder().setMaxResults(NUM_DETECTIONS).setLabelAllowList(allowedList).build();
        objectDetector = ObjectDetector.createFromFileAndOptions(context, modelFilename, options);
    }

    @Override
    public List<Recognition> recognizeImage(final Bitmap bitmap) {
        // Log this method so that it can be analyzed with systrace.
        Trace.beginSection("recognizeImage");
        List<Detection> results = objectDetector.detect(TensorImage.fromBitmap(bitmap));

        // Converts a list of {@link Detection} objects into a list of {@link Recognition} objects
        // to match the interface of other inference method, such as using the <a
        // href="https://github.com/tensorflow/examples/tree/master/lite/examples/object_detection/android/lib_interpreter">TFLite
        // Java API.</a>.
        final ArrayList<Recognition> recognitions = new ArrayList<>();
        int cnt = 0;
        for (Detection detection : results) {
            Optional<Integer> key = getKey(objek, detection.getCategories().get(0).getLabel());
            Log.d("taskAPI",
                    key.map(String::valueOf).orElse("999")
            );
            recognitions.add(
                    new Recognition(
                            "" + cnt++,
                            key.orElse(999),
                            detection.getCategories().get(0).getLabel(),
                            detection.getCategories().get(0).getScore(),
                            detection.getBoundingBox()));
        }
        Trace.endSection(); // "recognizeImage"
        return recognitions;
    }

    @Override
    public void enableStatLogging(final boolean logStats) {
    }

    @Override
    public String getStatString() {
        return "";
    }

    @Override
    public void close() {
        if (objectDetector != null) {
            objectDetector.close();
        }
    }

    @Override
    public void setNumThreads(int numThreads) {
        if (numThreads != 1) {
            throw new IllegalArgumentException(
                    "Manipulating the numbers of threads is not allowed in the Task"
                            + " library currently. The current implementation runs on single thread.");
        }
    }

    @Override
    public void setUseNNAPI(boolean isChecked) {
        throw new UnsupportedOperationException(
                "Manipulating the hardware accelerators is not allowed in the Task"
                        + " library currently. Only CPU is allowed.");
    }

    private static Optional<Integer> getKey(
            Map<Integer, String> map, String value) {

        return map
                .entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .findFirst();

    }


    private static Map<Integer, String> createObjek() {
        Map<Integer, String> objek = new HashMap<>();
        objek.put(0, "person");
        objek.put(10, "fire hydrant");
        objek.put(12, "stop sign");
        objek.put(13, "parking meter");
        objek.put(14, "bench");
        objek.put(26, "backpack");
        objek.put(27, "umbrella");
        objek.put(32, "suitcase");
        objek.put(36, "sports ball");
        objek.put(51, "banana");
        objek.put(61, "chair");
        objek.put(62, "couch");
        objek.put(63, "potted plant");
        objek.put(64, "bed");
        objek.put(66, "dining table");
        objek.put(71, "tv");
        objek.put(77, "microwave");
        objek.put(78, "oven");
        objek.put(79, "toaster");
        objek.put(80, "sink");
        objek.put(81, "refrigerator");
        objek.put(84, "clock");
        objek.put(86, "vase");
        return objek;
    }
}
