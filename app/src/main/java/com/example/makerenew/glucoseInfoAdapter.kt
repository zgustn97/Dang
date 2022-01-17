package com.example.makerenew

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class glucoseInfoAdapter(val gluInfoList: ArrayList<glucoseInfo>) : Adapter<glucoseInfoAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): glucoseInfoAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view)

    }

    override fun onBindViewHolder(holder: glucoseInfoAdapter.CustomViewHolder, position: Int) {
        holder.timekorean.text = gluInfoList.get(position).timekorean
        holder.time.text = gluInfoList.get(position).time
        holder.gluco.text = gluInfoList.get(position).gluco
    }

    override fun getItemCount(): Int {
        return gluInfoList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timekorean = itemView.findViewById<TextView>(R.id.tv_timekorean)
        val time = itemView.findViewById<TextView>(R.id.tv_time)
        val gluco = itemView.findViewById<TextView>(R.id.tv_glu)
    }



}