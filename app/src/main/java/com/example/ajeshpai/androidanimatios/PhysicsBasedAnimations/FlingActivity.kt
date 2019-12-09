package com.example.ajeshpai.androidanimatios.PhysicsBasedAnimations

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import com.example.ajeshpai.androidanimatios.R

class FlingActivity : AppCompatActivity() {

    private val mViewTobeFlung by lazy {
        findViewById<ImageView>(R.id.iv_translate_fling)
    }
    private val mTvFlingDistance by lazy {
        findViewById<TextView>(R.id.tv_fling_distance)
    }
    private val mMainLayout by lazy {
        findViewById<RelativeLayout>(R.id.main_layout)
    }
    private var maxTranslationX: Int = 0
    private var maxTranslationY: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fling)

        val gestureDetector = GestureDetector(this, mGestureListener)

        mViewTobeFlung.setOnTouchListener(View.OnTouchListener { v, event -> gestureDetector.onTouchEvent(event) })


        mMainLayout.getViewTreeObserver().addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                maxTranslationX = mMainLayout.getWidth() - mViewTobeFlung.getWidth()
                maxTranslationY = mMainLayout.getHeight() - mViewTobeFlung.getHeight()
                //As only wanted the first call back, so now remove the listener
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
                    mMainLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this)
                else
                    mMainLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this)
            }
        })
    }

    private val mGestureListener = object : GestureDetector.SimpleOnGestureListener() {

        //Constants
        private val MIN_DISTANCE_MOVED = 50
        private val MIN_TRANSLATION = 0f
        private val FRICTION = 1.1f

        override fun onDown(arg0: MotionEvent): Boolean {
            return true
        }

        override fun onFling(downEvent: MotionEvent, moveEvent: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            //downEvent : when user puts his finger down on the view
            //moveEvent : when user lifts his finger at the end of the movement
            val distanceInX = Math.abs(moveEvent.rawX - downEvent.rawX)
            val distanceInY = Math.abs(moveEvent.rawY - downEvent.rawY)

            mTvFlingDistance.setText("distanceInX : $distanceInX\ndistanceInY : $distanceInY")

            if (distanceInX > MIN_DISTANCE_MOVED) {
                //Fling Right/Left
                val flingX = FlingAnimation(mViewTobeFlung, DynamicAnimation.TRANSLATION_X)
                flingX.setStartVelocity(velocityX)
                        .setMinValue(MIN_TRANSLATION) // minimum translationX property
                        .setMaxValue(maxTranslationX.toFloat())  // maximum translationX property
                        .setFriction(FRICTION)
                        .start()
            } else if (distanceInY > MIN_DISTANCE_MOVED) {
                //Fling Down/Up
                val flingY = FlingAnimation(mViewTobeFlung, DynamicAnimation.TRANSLATION_Y)
                flingY.setStartVelocity(velocityY)
                        .setMinValue(MIN_TRANSLATION)  // minimum translationY property
                        .setMaxValue(maxTranslationY.toFloat()) // maximum translationY property
                        .setFriction(FRICTION)
                        .start()
            }

            return true
        }
    }
}