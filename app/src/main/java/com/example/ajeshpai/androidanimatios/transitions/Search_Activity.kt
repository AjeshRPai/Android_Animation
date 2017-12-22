package com.example.ajeshpai.androidanimatios.transitions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.ajeshpai.androidanimatios.R
import kotlinx.android.synthetic.main.search_view.*

class Search_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_view)
    }

    override fun onResume() {
        super.onResume()
        val adapter=SearchAdapter(getDummyValues())
        recycler_view.setLayoutManager(LinearLayoutManager(getApplicationContext()))
        recycler_view.adapter=adapter
    }


    private fun getDummyValues():List<String>{
       return listOf<String>("January","February","March")
    }
}
