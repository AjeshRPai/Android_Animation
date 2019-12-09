package com.example.ajeshpai.androidanimatios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ajeshpai.androidanimatios.PhysicsBasedAnimations.IndexPhysicsBasedActivity
import com.example.ajeshpai.androidanimatios.transitions.IndexTransitionActivity
import kotlinx.android.synthetic.main.activity_index2.*
import org.jetbrains.anko.startActivity

class IndexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index2)


        button4.setOnClickListener { startActivity<IndexPhysicsBasedActivity>() }

        button5.setOnClickListener { startActivity<IndexTransitionActivity>() }
    }
}
