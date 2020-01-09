package com.example.ajeshpai.androidanimatios.cardSelectionSceneform

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.dynamicanimation.animation.FloatPropertyCompat
import com.example.ajeshpai.androidanimatios.R
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.Material
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Texture
import kotlinx.android.synthetic.main.activity_card_selection.*
import java.lang.Math.*

class CardSelectionActivity : AppCompatActivity() {


    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_selection)

        val cardTextures =   loadTexture("card_material.mat", Texture.Usage.COLOR).build().get()

         ModelRenderable.builder()
                .setSource(this@CardSelectionActivity, Uri.parse("card_material.mat"))
                .build()
                .thenApply { model ->
                     model.material.setTexture("card_material", cardTextures) }.

        addCardToScene(renderable)



    }

    internal fun Context.loadTexture(
            source:String,
            usage: Texture.Usage
    ): Texture.Builder =
            Texture.builder()
                    .setSource(this, Uri.parse(source))
                    .setUsage(usage)
                    .setSampler(
                            Texture.Sampler.builder()
                                    .setMagFilter(Texture.Sampler.MagFilter.LINEAR)
                                    .setMinFilter(Texture.Sampler.MinFilter.LINEAR_MIPMAP_LINEAR)
                                    .build()
                    )


    private val CARD_STARTING_Y_AXIS_ANGLE: Float = 1.0f
    private val MODEL_SFB_PATH: Int = R.raw.card_material
    private val CARD_POSITION_X_AXIS: Float =  1.0f
    private val CARD_POSITION_Y_AXIS: Float  = 1.0f
    private val CARD_POSITION_Z_AXIS: Float = 1.0f
    private val CAMERA_POSITION_X_AXIS: Float = 1.0f
    private val CAMERA_POSITION_Y_AXIS: Float = 1.0f
    private val CAMERA_POSITION_Z_AXIS: Float = 1.0f
    private val CAMERA_FOCAL_LENGTH: Float  = 1.0f
    private val CAMERA_SCALE_HEIGHT: Float = 1.0f
    private val CAMERA_SCALE_WIDTH: Float = 1.0f

    private val card3dNode = Node().apply {
        localPosition = Vector3(CARD_POSITION_X_AXIS, CARD_POSITION_Y_AXIS, CARD_POSITION_Z_AXIS)
        localRotation = getRotationQuaternion(CARD_STARTING_Y_AXIS_ANGLE.toFloat())
        name = CARD_ID
    }

    data class CardRender(val material: Material)

    fun addCardToScene(modelRenderable: ModelRenderable) {
        modelRenderable.material = currentCard.value
        with(card3dNode) {
            setParent(sceneView.scene)
            renderable = modelRenderable
            localScale = modelRenderable.computeScaleVector(targetSize = 1.5f)
            currentCard.renderCard()
        }
        with(sceneView.scene) {
            camera.localScale = Vector3(CAMERA_SCALE_WIDTH, CAMERA_SCALE_HEIGHT, CAMERA_FOCAL_LENGTH)
            camera.localPosition = Vector3(CAMERA_POSITION_X_AXIS, CAMERA_POSITION_Y_AXIS, CAMERA_POSITION_Z_AXIS)
            sunlight?.let {
                it.worldPosition = Vector3.back()
                it.light = cardSceneSunLight
            }
            addChild(card3dNode)
        }
    }

    private var lastDeltaYAxisAngle = 1.0f
    private var screenDensity = 1.0f

    private val quaternion = Quaternion()
    private val rotateVector = Vector3.up()
    private fun getRotationQuaternion(deltaYAxisAngle: Float): Quaternion {
        lastDeltaYAxisAngle = deltaYAxisAngle
        return quaternion.apply {
            val arc = toRadians(deltaYAxisAngle.toDouble())
            val axis = sin(arc / 2.0)
            x = rotateVector.x * axis.toFloat()
            y = rotateVector.y * axis.toFloat()
            z = rotateVector.z * axis.toFloat()
            w = cos(arc / 2.0).toFloat()
            normalize()
        }
    }

    val FLING_ANIMATION_FRICTION = 1.0f
    val CARD_ROTATION_FRICTION = 1.0f
    val SWIPE_THRESHOLD_VELOCITY = 1.0f


    abstract class CardProperty(name: String) : FloatPropertyCompat<Node>(name)
    private val rotationProperty: CardProperty = object : CardProperty("rotation") {
        override fun setValue(card: Node, value: Float) {
            card.localRotation = getRotationQuaternion(value)
        }
        override fun getValue(card: Node): Float = card.localRotation.y
    }
    private var animation: FlingAnimation = FlingAnimation(card3dNode, rotationProperty).apply {
        friction = FLING_ANIMATION_FRICTION
        minimumVisibleChange = DynamicAnimation.MIN_VISIBLE_CHANGE_ROTATION_DEGREES
    }

    inner class FlingGestureDetector : GestureDetector.SimpleOnGestureListener() {

        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            val deltaX = -(distanceX / screenDensity) / CARD_ROTATION_FRICTION
            card3dNode.localRotation = getRotationQuaternion(lastDeltaYAxisAngle + deltaX)
            return true
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            if (Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                val deltaVelocity = (velocityX / screenDensity) / CARD_ROTATION_FRICTION
                startAnimation(deltaVelocity)
            }
            return true
        }
    }

    private fun startAnimation(velocity: Float) {
        if (!animation.isRunning) {
            animation.setStartVelocity(velocity)
            animation.setStartValue(lastDeltaYAxisAngle)
            animation.start()
        }
    }





}
