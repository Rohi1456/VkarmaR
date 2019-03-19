package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vkarmaedu.vkarma.adapters.TeacherAttendanceAdapter
import com.vkarmaedu.vkarma.dataModels.TeacherAttendance
import kotlinx.android.synthetic.main.fragment_teachers_attendance.view.*
import androidx.recyclerview.widget.GridLayoutManager
import com.vkarmaedu.vkarma.R
import kotlinx.android.synthetic.main.list_teacher_attendeance.view.*


public class TeacherAttendanceFragment :Fragment(),TeacherAttendanceAdapter.OnItemClickListener
{


    private val myAdapter = TeacherAttendanceAdapter(this)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val root = inflater.inflate(com.vkarmaedu.vkarma.R.layout.fragment_teachers_attendance, container, false)
        val list = ArrayList<TeacherAttendance>()
        for (i in 0..10) {
        list.add(TeacherAttendance("103","true"))
        }
        myAdapter.changeData(list)
        root.attendanceRV.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = myAdapter
        }
        return root
    }
    override fun onItemClickListener(position: Int, itemView: View) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        itemView.llteacher.setBackgroundColor(resources.getColor(R.color.green))
        itemView.teacherIdTV.setCompoundDrawablesWithIntrinsicBounds(null,null,resources.getDrawable(R.drawable.ic_verified2),null)

    }
}