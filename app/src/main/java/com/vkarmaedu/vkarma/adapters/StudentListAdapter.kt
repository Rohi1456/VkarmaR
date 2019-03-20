package com.vkarmaedu.vkarma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.dataModels.Student
import kotlinx.android.synthetic.main.list_students.view.*

class StudentListAdapter(private val listener : OnItemClickListener) : RecyclerView.Adapter<StudentListAdapter.MyViewHolder>() {
    private var list = emptyList<Student>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_students, parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val student = list[position]
        holder.name.text = student.name
        holder.roll_no.text = "Roll No. ${student.rollNo}"
        //TODO: image download using glide

        holder.itemView.setOnClickListener { listener.onItemClickListener(student) }
    }

    fun updateData(list: List<Student>){
        this.list = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClickListener(student: Student)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.name
        val roll_no : TextView = itemView.roll_no
        val avatar : ImageView = itemView.avatar
    }
}