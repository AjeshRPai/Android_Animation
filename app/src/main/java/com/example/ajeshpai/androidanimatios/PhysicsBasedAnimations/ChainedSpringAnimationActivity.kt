package com.example.ajeshpai.androidanimatios.PhysicsBasedAnimations

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.example.ajeshpai.androidanimatios.R
import kotlinx.android.synthetic.main.activity_chained_spring_animation.*

class ChainedSpringAnimationActivity : AppCompatActivity() {



    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chained_spring_animation)



        val animFirstFollowerX = SpringAnimation(iv_first_follower, DynamicAnimation.TRANSLATION_X)
        val animFirstFollowerY = SpringAnimation(iv_first_follower, DynamicAnimation.TRANSLATION_Y)

        val animSecondFollowerX = SpringAnimation(iv_second_follower, DynamicAnimation.TRANSLATION_X)
        val animSecondFollowerY = SpringAnimation(iv_second_follower, DynamicAnimation.TRANSLATION_Y)


        iv_lead!!.setOnTouchListener(object : View.OnTouchListener {
            internal var lastX: Float = 0.toFloat()
            internal var lastY: Float = 0.toFloat()

            override fun onTouch(v: View, motionEvent: MotionEvent): Boolean {

                if (motionEvent.actionMasked == MotionEvent.ACTION_MOVE) {
                    val deltaX = motionEvent.rawX - lastX
                    val deltaY = motionEvent.rawY - lastY

                    iv_lead!!.translationX = deltaX + iv_lead!!.translationX
                    iv_lead!!.translationY = deltaY + iv_lead!!.translationY

                    animFirstFollowerX.animateToFinalPosition(iv_lead!!.translationX)
                    animFirstFollowerY.animateToFinalPosition(iv_lead!!.translationY)
                }

                lastX = motionEvent.rawX
                lastY = motionEvent.rawY

                return true
            }
        })

        animFirstFollowerX.addUpdateListener { animation, value, velocity -> animSecondFollowerX.animateToFinalPosition(value) }

        animFirstFollowerY.addUpdateListener { animation, value, velocity -> animSecondFollowerY.animateToFinalPosition(value) }
    }
}
