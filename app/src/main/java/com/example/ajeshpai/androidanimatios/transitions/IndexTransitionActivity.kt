package com.example.ajeshpai.androidanimatios.transitions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ajeshpai.androidanimatios.R
import kotlinx.android.synthetic.main.activity_index.*
import org.jetbrains.anko.startActivity

class IndexTransitionActivity : AppCompatActivity() {


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
