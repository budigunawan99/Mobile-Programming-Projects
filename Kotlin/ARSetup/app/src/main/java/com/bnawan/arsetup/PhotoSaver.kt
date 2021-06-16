package com.bnawan.arsetup

import android.app.Activity
import android.content.ContentValues
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.HandlerThread
import android.provider.MediaStore
import android.util.Log
import android.view.PixelCopy
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.ar.sceneform.ArSceneView
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class PhotoSaver(private val activity: Activity) {
    //api below 28
    private fun generateFilename(): String? {
        val date = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(Date())
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)?.absolutePath + "/ARSetup/${date}_captured.jpg"
    }

    //for api below 28
    private fun saveBitmapToGallery(bmp: Bitmap, filename: String) {
        val out = File(filename)
        if(!out.parentFile.exists()) {
            out.parentFile.mkdirs()
        }
        try {
            val outputStream = FileOutputStream(filename)
            saveDataToGallery(bmp, outputStream)
            MediaScannerConnection.scanFile(activity, arrayOf(filename), null, null)
        } catch (e: IOException) {
            Toast.makeText(activity, "Failed to save photo to gallery.", Toast.LENGTH_LONG).show()
        }
    }

    //api > 28
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun saveBitmapToGallery(bmp: Bitmap){
        val date = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(Date())
        val contentValues = ContentValues().apply{
            put(MediaStore.MediaColumns.DISPLAY_NAME, "${date}_captured.jpg")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/ARSetup")
        }

        val uri = activity.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )
        activity.contentResolver.openOutputStream(uri ?: return).use {outputStream ->
            outputStream?.let {
                try {
                    saveDataToGallery(bmp, it)
                } catch (e: IOException) {
                    Toast.makeText(activity, "Failed to save bitmap to gallery", Toast.LENGTH_LONG).show()
                }
            }

        }

    }

    private fun saveDataToGallery(bmp:Bitmap, outputStream: OutputStream){
        val outputData = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, outputData)
        outputData.writeTo(outputStream)
        outputStream.flush()
        outputStream.close()
    }

    fun takePhoto(arSceneView: ArSceneView) {
        val bmp =
            Bitmap.createBitmap(arSceneView.width, arSceneView.height, Bitmap.Config.ARGB_8888)
        val handlerThread = HandlerThread("PixelCopyThread")

        handlerThread.start()

        PixelCopy.request(arSceneView, bmp, { result ->
            if (result == PixelCopy.SUCCESS) {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    val filename = generateFilename()
                    Log.d("PixelCopy", "Operating System Pie to under")
                    saveBitmapToGallery(bmp, filename ?: return@request)
                }else{
                    Log.d("PixelCopy", "Operating System above Pie")
                    saveBitmapToGallery(bmp)
                }
                activity.runOnUiThread {
                    Toast.makeText(activity, "Successfully took photo!", Toast.LENGTH_LONG).show()
                }
            } else {
                activity.runOnUiThread {
                    Toast.makeText(activity, "Failed to take photo.", Toast.LENGTH_LONG).show()
                }
            }
        }, Handler(handlerThread.looper))

    }
}
