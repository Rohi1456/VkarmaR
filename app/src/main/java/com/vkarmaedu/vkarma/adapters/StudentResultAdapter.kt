package com.vkarmaedu.vkarma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.dataModels.Results
import kotlinx.android.synthetic.main.list_result.view.*

class StudentResultAdapter(private val listener :OnItemClickListener) : RecyclerView.Adapter<StudentResultAdapter.MyViewHolder>() {
    private var list = emptyList<Results>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_result, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = list[position]
        holder.subject.text = result.subject
        holder.marks.text = "${result.obtained}/${result.total}"
        if (result.total != 0)
        holder.progress.progress = result.obtained * 100 / result.total

        holder.itemView.setOnClickListener { listener.onItemClickListener(result) }
    }

    fun updateData(list: List<Results>){
        this.list = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClickListener(result : Results)
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val subject : TextView = itemView.subject
        val marks : TextView = itemView.marks
        val progress : ProgressBar = itemView.progressBar
    }
}