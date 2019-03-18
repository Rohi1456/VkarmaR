package com.vkarmaedu.vkarma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.dataModels.TeacherAttendance
import com.vkarmaedu.vkarma.fragment.TeacherAttendanceFragment
import kotlinx.android.synthetic.main.list_teacher_attendeance.view.*

class TeacherAttendanceAdapter(private val listener: TeacherAttendanceFragment) : RecyclerView.Adapter<TeacherAttendanceAdapter.MyViewHolder>()  {


    private var list : List<TeacherAttendance> = emptyList()


    fun changeData(list : List<TeacherAttendance>){
        this.list = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return TeacherAttendanceAdapter.MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_teacher_attendeance, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    public interface OnItemClickListener{
        fun onItemClickListener(position: Int, itemView: View)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val teacherAttendance = list[position]

        holder.id.text = teacherAttendance.teacherId
        holder.itemView.setOnClickListener { listener.onItemClickListener(position,holder.itemView) }
    }

    class MyViewHolder(val item : View) : RecyclerView.ViewHolder(item) {
        val id  : TextView = item.teacherIdTV
    }
}
