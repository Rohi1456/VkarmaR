package com.vkarmaedu.vkarma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.dataModels.Results
import kotlinx.android.synthetic.main.fragment_teacher_detail.view.*
import kotlinx.android.synthetic.main.list_compare.view.*
import kotlinx.android.synthetic.main.list_test.view.*

class CompareResultsAdapter : RecyclerView.Adapter<CompareResultsAdapter.MyViewHolder>() {
    private var list = emptyList<Results>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_compare, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = list[position]
        holder.marks.text = "${result.obtained}/${result.total}"
        holder.testName.text = result.subject
       /* if (result.total != 0)
            holder.progressBar.progress = result.obtained * 100 / result.total*/
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val marks : TextView = itemView.marksTV
        val testName : TextView = itemView.subNameTVID
//        val progressBar : ProgressBar = itemView.progressBar
    }

    fun updateData(list : List<Results>){
        this.list = list
        notifyDataSetChanged()
    }
}