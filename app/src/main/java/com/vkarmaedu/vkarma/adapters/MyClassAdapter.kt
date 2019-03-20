package com.vkarmaedu.vkarma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vkarmaedu.vkarma.R
import kotlinx.android.synthetic.main.list_classes.view.*

class MyClassAdapter(private val listener : OnItemClickListener) : RecyclerView.Adapter<MyClassAdapter.MyViewHolder>() {
    private var list = emptyList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_classes, parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val clas = list[position]
        holder.clas.text = clas

        holder.itemView.setOnClickListener { listener.onItemClickListener(clas) }
    }

    fun updateData(list: List<String>){
        this.list = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClickListener(clas : String)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clas : TextView = itemView.class_text
    }
}