package com.example.ajeshpai.androidanimatios.transitions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintSet
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import android.transition.*
import android.view.Gravity
import android.view.View
import com.example.ajeshpai.androidanimatios.R
import kotlinx.android.synthetic.main.transitions_alarm_to_mapview.*

class AlarmToMapViewTransition : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transitions_alarm_to_mapview)

        val idleSet= ConstraintSet()
        val triggeredSet= ConstraintSet()

        idleSet.clone(first_container)
        triggeredSet.clone(first_container)

        val transition=TransitionSet()
                .addTransition(Fade())
                    .addTarget(alarm_title)
                    .addTarget(alarm_description)
                    .setDuration(100)
                .addTransition(Slide(Gravity.TOP))
                    .addTarget(white_background)
                    .setInterpolator(FastOutSlowInInterpolator())
                    .setDuration(300)
                .addTransition(ChangeBounds())
                    .setInterpolator(FastOutSlowInInterpolator())
                    .setDuration(400)
                .addTransition(Fade())
                    .addTarget(help_title)
                    .addTarget(help_description)
                    .setDuration(500)


        triggeredSet.setVisibility(help_title.id, View.VISIBLE)
        triggeredSet.setVisibility(help_description.id,View.VISIBLE)

        triggeredSet.setVisibility(alarm_title.id,View.GONE)
        triggeredSet.setVisibility(alarm_description.id,View.GONE)
        triggeredSet.setVisibility(white_background.id,View.VISIBLE)

        triggeredSet.connect(R.id.white_background, ConstraintSet.BOTTOM,R.id.guideline2, ConstraintSet.TOP)


        idleSet.setVisibility(help_title.id, View.GONE)
        idleSet.setVisibility(help_description.id,View.GONE)

        idleSet.setVisibility(alarm_title.id,View.VISIBLE)
        idleSet.setVisibility(alarm_description.id,View.VISIBLE)
        idleSet.setVisibility(white_background.id,View.GONE)


        transtition_button.setOnClickListener {
            TransitionManager.beginDelayedTransition(first_container,transition)
            triggeredSet.applyTo(first_container)
        }

        button3.setOnClickListener {
            TransitionManager.beginDelayedTransition(first_container,transition)
            idleSet.applyTo(first_container)
        }
    }
}
