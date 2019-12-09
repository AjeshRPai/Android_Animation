package com.example.ajeshpai.androidanimatios.PhysicsBasedAnimations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.example.ajeshpai.androidanimatios.R

class SpringAnimationActivity : AppCompatActivity() {


    private val mSpringTranslationXAnimation by lazy{
        SpringAnimation(mViewToBeAnimated,
                object : FloatPropertyCompat<View>("translationX") {

                    override fun getValue(view: View): Float {
                        return view.translationX
                    }

                    override fun setValue(view: View, value: Float) {
                        view.translationX = value
                    }
                })
    }
    private val mSpringTranslationYAnimation by lazy {
        SpringAnimation(mViewToBeAnimated,object :FloatPropertyCompat<View>("translationY"){
            override fun getValue(view: View): Float {
                return view.translationY
            }

            override fun setValue(view: View, value: Float) {
                view.translationY = value
            }
        })
    }

    private val mViewToBeAnimated by lazy{
         findViewById<ImageView>(R.id.iv_translate_spring)
    }
    private var mXDiffInTouchPointAndViewTopLeftCorner: Float = 0.toFloat()
    private var mYDiffInTouchPointAndViewTopLeftCorner: Float = 0.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.translate_spring_animations)

        mViewToBeAnimated.setOnTouchListener(onTouchListener)

        // final position provided is 0, because we want to ensure translationX/translationY of view
        // from wherever the view moved due to touch gesture to come back to its initial
        // translationX/translationY position which is 0.

        val springForceX = SpringForce(0f)
        springForceX.stiffness = SpringForce.STIFFNESS_MEDIUM
        springForceX.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
        mSpringTranslationXAnimation!!.setSpring(springForceX)



        val springForceY = SpringForce(0f)
        springForceY.stiffness = SpringForce.STIFFNESS_MEDIUM
        springForceY.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
        mSpringTranslationYAnimation!!.setSpring(springForceY)
    }



    private val onTouchListener = View.OnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {


                Log.e("Mouse","onTouched to Drag")

                // calculate the difference between touch point(event.getRawX()) on view & view's top left corner(v.getX())
                mXDiffInTouchPointAndViewTopLeftCorner = event.rawX - v.x
                mYDiffInTouchPointAndViewTopLeftCorner = event.rawY - v.y

            }
            MotionEvent.ACTION_MOVE -> {

                Log.e("Mouse","Dragging")

                //   Log.e("view values","view.x ="+v.x.toString()+" view.y "+v.y)
             //   Log.e("move values values","difference.x ="+mXDiffInTouchPointAndViewTopLeftCorner.toString()+" difference.y "+mYDiffInTouchPointAndViewTopLeftCorner.toString())

                val newTopLeftX = event.rawX - mXDiffInTouchPointAndViewTopLeftCorner
                val newTopLeftY = event.rawY - mYDiffInTouchPointAndViewTopLeftCorner
                mViewToBeAnimated!!.setX(newTopLeftX)
                mViewToBeAnimated!!.setY(newTopLeftY)
            }
            MotionEvent.ACTION_UP -> {

                //When the Mouse is released

                Log.e("Mouse","Released")

                mSpringTranslationXAnimation.start()
                mSpringTranslationYAnimation.start()
            }
        }
        true
    }
}
