package com.vkarmaedu.vkarma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.dataModels.Homework
import com.vkarmaedu.vkarma.utility.dateFormat
import kotlinx.android.synthetic.main.list_homework_student.view.*

class HomeworkStudentAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<ViewHolder>() {
    private var list : List<Homework> = emptyList()
    private val today = dateFormat.format(System.currentTimeMillis())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolderHomework(LayoutInflater.from(parent.context).inflate(R.layout.list_homework_student, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homework = list[position]
        holder as ViewHolderHomework

        val homeworkDate = dateFormat.format(homework.date)
        if (homeworkDate != today){
            holder.date.visibility = View.VISIBLE
            holder.date.text = homeworkDate
        }

        holder.subject.text = homework.subName
        holder.content.text = homework.text
        holder.itemView.setOnClickListener{ listener.onItemClickListener(homework) }
    }

    class ViewHolderHomework(view: View) : ViewHolder(view) {
        val date =  view.date
        val subject = view.subject_name
        val content = view.content
    }

    fun changeData(list : List<Homework>){
        this.list = list
        notifyDataSetChanged()
    }

    public interface OnItemClickListener{
        fun onItemClickListener(homework: Homework)
    }
}