package com.example.ajeshpai.androidanimatios.Transitions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.transition.*
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.ajeshpai.androidanimatios.R
import kotlinx.android.synthetic.main.activity_home_view__alarm_view_switching.*
import kotlinx.android.synthetic.main.home_view.*
import android.view.ViewGroup
import android.widget.FrameLayout


class HomeView_AlarmViewSwitching : AppCompatActivity() {

    private val alarmview by lazy {
        layoutInflater.inflate(R.layout.alarm_view,view_group,false)
    }
    private val homeview by lazy {
        layoutInflater.inflate(R.layout.home_view,view_group,false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_view__alarm_view_switching)

        val fab=homeview.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val fab2=alarmview.findViewById<FloatingActionButton>(R.id.floatingActionButton2)

        val content_container=alarmview.findViewById<ConstraintLayout>(R.id.content_container)
        val homeview_container=homeview.findViewById<ConstraintLayout>(R.id.homeview_container)
        val home_view=homeview.findViewById<FrameLayout>(R.id.home_view)


        val transition=TransitionSet()
                .addTransition(Slide(Gravity.BOTTOM)
                        .addTarget(content_container)
                        .setInterpolator(LinearOutSlowInInterpolator())
                )
                .addTransition(ChangeBounds()
                        .addTarget(fab)
                        .addTarget(fab2)
                )

        fab.setOnClickListener {
            TransitionManager.beginDelayedTransition(view_group,transition)
            if (alarmview.getParent() != null)
                (alarmview.getParent() as ViewGroup).removeView(alarmview)
            view_group.addView(alarmview)
            home_view.removeView(fab)

        }

        fab2.setOnClickListener {
            view_group.removeAllViews()
            view_group.addView(homeview)
            home_view.addView(fab)
        }

        view_group.addView(homeview)
    }
}
