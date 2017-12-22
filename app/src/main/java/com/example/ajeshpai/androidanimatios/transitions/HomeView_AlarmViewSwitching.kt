package com.example.ajeshpai.androidanimatios.transitions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.transition.*
import android.view.Gravity
import com.example.ajeshpai.androidanimatios.R
import android.view.ViewGroup
import android.widget.FrameLayout


class HomeView_AlarmViewSwitching : AppCompatActivity() {

    private val alarmview by lazy {
        layoutInflater.inflate(R.layout.alarm_view,viewgroup,false)
    }

    private val homeview by lazy {
        layoutInflater.inflate(R.layout.home_view,viewgroup,false)
    }

    private val homeview_fab by lazy {
        homeview.findViewById<FloatingActionButton>(R.id.floatingActionButton)
    }

    private val alarmview_fab by lazy {
        alarmview.findViewById<FloatingActionButton>(R.id.floatingActionButton2)
    }

    private val content_container by lazy {
        alarmview.findViewById<ConstraintLayout>(R.id.content_container)
    }

    private val homeview_container by lazy {
        homeview.findViewById<ConstraintLayout>(R.id.homeview_container)
    }

    private val home_view by lazy {
        homeview.findViewById<FrameLayout>(R.id.home_view)
    }

    private val viewgroup by lazy {
        findViewById<FrameLayout>(R.id.view_group)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_view__alarm_view_switching)

        val transition=TransitionSet()
                .addTransition(Slide(Gravity.BOTTOM)
                        .addTarget(content_container)
                        .setInterpolator(LinearOutSlowInInterpolator())
                )
                .addTransition(ChangeBounds()
                        .addTarget(homeview_fab)
                        .addTarget(alarmview_fab)
                )

        val back_transition=TransitionSet()
                .addTransition(Slide(Gravity.BOTTOM)
                        .addTarget(content_container)
                        .setInterpolator(LinearOutSlowInInterpolator())
                )
                .addTransition(ChangeBounds()
                        .addTarget(homeview_fab)
                        .addTarget(alarmview_fab)
                )

        homeview_fab.setOnClickListener {
            TransitionManager.beginDelayedTransition(viewgroup,transition)
            if (alarmview.getParent() != null)
                (alarmview.getParent() as ViewGroup).removeView(alarmview)
            viewgroup.addView(alarmview)
            home_view.removeView(homeview_fab)

        }

        alarmview_fab.setOnClickListener {
            TransitionManager.beginDelayedTransition(viewgroup,back_transition)
            home_view.addView(homeview_fab)
            viewgroup.removeAllViews()
            viewgroup.addView(homeview)
        }

        viewgroup.addView(homeview)
    }
}
