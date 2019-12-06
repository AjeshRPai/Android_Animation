package com.example.ajeshpai.androidanimatios

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ajeshpai.androidanimatios.transitions.AlarmToMapViewTransition
import com.example.ajeshpai.androidanimatios.transitions.HomeView_AlarmViewSwitching
import com.example.ajeshpai.androidanimatios.transitions.SearchViewTransitionActivity
import kotlinx.android.synthetic.main.activity_index.*
import org.jetbrains.anko.startActivity

class IndexActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)


        btn_alarm_to_map_view.setOnClickListener {
            startActivity<AlarmToMapViewTransition>()
        }

        btn_home_view_alarm_view.setOnClickListener {
            startActivity<HomeView_AlarmViewSwitching>()
        }

        btn_search_view_transition.setOnClickListener {
            startActivity<SearchViewTransitionActivity>()
        }




    }
}
