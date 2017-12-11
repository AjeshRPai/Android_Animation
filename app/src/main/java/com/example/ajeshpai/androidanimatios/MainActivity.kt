package com.example.ajeshpai.androidanimatios

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ajeshpai.androidanimatios.PhysicsBasedAnimations.ChainedSpringAnimationActivity
import com.example.ajeshpai.androidanimatios.PhysicsBasedAnimations.FlingActivity
import com.example.ajeshpai.androidanimatios.PhysicsBasedAnimations.SpringAnimationActivity
import com.example.ajeshpai.androidanimatios.PhysicsBasedAnimations.TranslateAndRotateSpringAnimation
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_translate_spring.setOnClickListener{
                startActivity<SpringAnimationActivity>()
        }
        btn_translate_fling.setOnClickListener {
            startActivity<FlingActivity>()
        }

        btn_translate_rotate_spring.setOnClickListener {
            startActivity<TranslateAndRotateSpringAnimation>()
        }

        btn_chained_spring.setOnClickListener {
            startActivity<ChainedSpringAnimationActivity>()
        }




    }
}
