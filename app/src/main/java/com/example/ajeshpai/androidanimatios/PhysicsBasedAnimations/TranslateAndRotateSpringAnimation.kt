package com.example.ajeshpai.androidanimatios.PhysicsBasedAnimations

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.animation.FloatPropertyCompat
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.example.ajeshpai.androidanimatios.R
import kotlinx.android.synthetic.main.activity_translate_and_rotate_spring_animation.*

class TranslateAndRotateSpringAnimation : AppCompatActivity() {


    private var mLinearLayout: LinearLayout? = null
    private var mBtnBringIt: Button? = null
    private val mSpringRotationAnimation: SpringAnimation? = null
    private val INITIAL_ROTATION = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate_and_rotate_spring_animation)

        mLinearLayout = findViewById<LinearLayout>(R.id.ll_list) as LinearLayout

        button_bring.setOnClickListener {
            val springForce = SpringForce(INITIAL_ROTATION)
            springForce.stiffness = SpringForce.STIFFNESS_VERY_LOW
            springForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY

            for (i in 0 until mLinearLayout!!.childCount) {
                val child = mLinearLayout!!.getChildAt(i)
                val rotationAnim: SpringAnimation
                // First, let's rotate each individual head, to do that, we need to
                // go through all the items on the list, and for each item we will need
                // to create a Spring Animation on its rotation property.
                rotationAnim = SpringAnimation(child,
                        object : FloatPropertyCompat<View>("rotation") {

                            override fun getValue(view: View): Float {
                                return view.rotation
                            }

                            override fun setValue(view: View, value: Float) {
                                view.rotation = value
                            }
                        })
                // Finally, we can set the start value of the animation to -30 so that each head rotate
                // from a -30 degree angle and then oscillate around 0 degree and gradually come to a stop.
                rotationAnim.setSpring(springForce).setStartValue(-60f).start()
            }

            // And finally we want the list to slide, we are going to create a Spring animation
            // on the translation property of the list.
            // We are also going to set the start value so the TRANSLATION_X will start from 400
            // and then slide back into its final position 0.

            val slideAnim = SpringAnimation(mLinearLayout,
                    object : FloatPropertyCompat<View>("translationX") {

                        override fun getValue(view: View): Float {
                            return view.translationX
                        }

                        override fun setValue(view: View, value: Float) {
                            view.translationX = value
                        }
                    }, 0f)
            slideAnim.spring.setStiffness(500f).dampingRatio = 0.4f
            slideAnim.setStartValue(400f).start()

        }



    }


}
