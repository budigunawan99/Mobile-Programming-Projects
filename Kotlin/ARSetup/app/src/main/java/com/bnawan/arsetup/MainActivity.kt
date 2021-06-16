package com.bnawan.arsetup

import android.annotation.SuppressLint
import android.media.CamcorderProfile
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.filament.Box
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.Color
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.CompletableFuture

const val BOTTOM_SHEET_PEEK_HEIGHT = 30f
const val DOUBLE_TAP_TOLERANCE_MS = 1000L

class MainActivity : AppCompatActivity() {
    lateinit var arFragment: ArFragment
    private lateinit var selectedModel: FishModel
    private val viewnodes = mutableListOf<Node>()
    private lateinit var photoSaver: PhotoSaver
    private lateinit var videoRecorder: VideoRecorder
    private var isRecording = false

    private var fish = mutableListOf(
        FishModel(R.drawable.angler, "ANGLER", R.raw.angler),
        FishModel(R.drawable.dolphin, "DOLPHIN", R.raw.dolphin),
        FishModel(R.drawable.narwhal, "NARWHAL", R.raw.narwhal),
        FishModel(R.drawable.shark, "SHARK", R.raw.shark),
        FishModel(R.drawable.whale, "WHALE", R.raw.whale)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomSheet()
        setupRecyclerView()
        arFragment = container as ArFragment
        setDoubleTapArPlaneListener()
        getCurrentScene().addOnUpdateListener {
            rotateViewNodesToUser()
        }
        setupFab()
        photoSaver = PhotoSaver(this)
        videoRecorder = VideoRecorder(this).apply {
            sceneView = arFragment.arSceneView
            setVideoQuality(CamcorderProfile.QUALITY_1080P, resources.configuration.orientation)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupFab() {
        btn_camera.setOnClickListener {
            if (!isRecording) {
                photoSaver.takePhoto(arFragment.arSceneView)
            }
        }
        btn_camera.setOnLongClickListener {
            isRecording = videoRecorder.recordingState()
            true
        }
        btn_camera.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP && isRecording) {
                isRecording = videoRecorder.recordingState()
                Toast.makeText(this, "Saved video to gallery", Toast.LENGTH_LONG).show()
                true
            }else false
        }

    }

    private fun rotateViewNodesToUser() {
        for (node in viewnodes) {
            node.renderable?.let {
                val cameraPosition = getCurrentScene().camera.worldPosition
                val viewNodePosition = node.worldPosition
                val direction = Vector3.subtract(cameraPosition, viewNodePosition)
                node.worldRotation = Quaternion.lookRotation(direction, Vector3.up())
            }
        }
    }

    private fun setDoubleTapArPlaneListener() {
        var firstTap = 0L
        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
            if (firstTap == 0L) {
                firstTap = System.currentTimeMillis()
            } else if (System.currentTimeMillis() - firstTap < DOUBLE_TAP_TOLERANCE_MS) {
                firstTap = 0L
                loadModel { modelRenderable, viewRenderable ->
                    addNodeToScene(hitResult.createAnchor(), modelRenderable, viewRenderable)
                }
            } else {
                firstTap = System.currentTimeMillis()
            }
        }
    }

    private fun setupRecyclerView() {
        rvModels.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvModels.adapter = FishModelAdapter(fish).apply {
            selectedModel.observe(this@MainActivity, Observer {
                this@MainActivity.selectedModel = it
                tvModel.text = it.title
            })
        }
    }

    private fun setupBottomSheet() {
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.peekHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            BOTTOM_SHEET_PEEK_HEIGHT,
            resources.displayMetrics
        ).toInt()

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                bottomSheet.bringToFront()
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {}

        })
    }

    private fun loadModel(callback: (ModelRenderable, ViewRenderable) -> Unit) {
        val modelRenderable =
            ModelRenderable.builder().setSource(this, selectedModel.modelResourceId).build()
        val viewRenderable = ViewRenderable.builder().setView(this, createDeleteButton()).build()

        CompletableFuture.allOf(modelRenderable, viewRenderable)
            .thenAccept { callback(modelRenderable.get(), viewRenderable.get()) }
            .exceptionally {
                Toast.makeText(this, "Error loading model: $it", Toast.LENGTH_LONG).show()
                null
            }
    }

    private fun createDeleteButton(): Button {
        return Button(this).apply {
            text = "Delete"
            setBackgroundColor(android.graphics.Color.RED)
            setTextColor(android.graphics.Color.WHITE)
        }
    }

    private fun getCurrentScene() = arFragment.arSceneView.scene

    private fun addNodeToScene(
        anchor: Anchor,
        modelRenderable: ModelRenderable,
        viewRenderable: ViewRenderable
    ) {
        val anchorNode = AnchorNode(anchor)
        val modelNode = TransformableNode(arFragment.transformationSystem).apply {
            renderable = modelRenderable
            setParent(anchorNode)
            getCurrentScene().addChild(anchorNode)
            select()
        }

        val viewNode = Node().apply {
            renderable = null
            setParent(modelNode)
            val box = modelNode.renderable?.collisionShape as com.google.ar.sceneform.collision.Box
            localPosition = Vector3(0f, box.size.y, 0f)
            viewRenderable.view.setOnClickListener {
                getCurrentScene().removeChild(anchorNode)
                viewnodes.remove(this)
            }
        }

        viewnodes.add(viewNode)
        modelNode.setOnTapListener { _, _ ->
            if (!modelNode.isTransforming) {
                if (viewNode.renderable == null) {
                    viewNode.renderable = viewRenderable
                } else {
                    viewNode.renderable = null
                }
            }
        }
    }
}