package com.example.ajeshpai.androidanimatios.transitions

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.example.ajeshpai.androidanimatios.R


class SearchAdapter(private val list: List<String>) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var result: TextView
        init {
            result = view.findViewById(R.id.result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.search_result_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result_value = list[position]
        holder.result.setText(result_value)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}