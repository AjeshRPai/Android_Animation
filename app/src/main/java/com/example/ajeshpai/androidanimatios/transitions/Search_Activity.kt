package com.example.ajeshpai.androidanimatios.transitions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.example.ajeshpai.androidanimatios.R
import kotlinx.android.synthetic.main.search_view.*

class Search_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_view)
    }

    override fun onResume() {
        super.onResume()
        recycler_view.visibility= View.VISIBLE
        val adapter=SearchAdapter(getDummyValues())
        recycler_view.setLayoutManager(LinearLayoutManager(getApplicationContext()))
        recycler_view.adapter=adapter

    }


    private fun getDummyValues():List<String>{
       return listOf<String>("January","February","March")
    }
}
