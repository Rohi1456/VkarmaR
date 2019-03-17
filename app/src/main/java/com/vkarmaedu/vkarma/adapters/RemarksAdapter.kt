package com.vkarmaedu.vkarma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.dataModels.Remarks
import kotlinx.android.synthetic.main.list_remark.view.*

class RemarksAdapter(private val listener : OnItemClickListener) : RecyclerView.Adapter<RemarksAdapter.MyViewHolder>() {
    private var list = emptyList<Remarks>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_remark, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val remark = list[position]
        holder.teacher.text = remark.teachName
        holder.content.text = remark.content
        holder.itemView.setOnClickListener { listener.onItemClickListener(remark) }
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val teacher = itemView.name
        val content = itemView.content
    }

    fun updateData(list : List<Remarks>){
        this.list = list
    }

    public interface OnItemClickListener {
        public fun onItemClickListener(remark: Remarks)
    }
}