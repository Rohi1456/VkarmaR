package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.StudentListAdapter
import com.vkarmaedu.vkarma.dataModels.Student
import com.vkarmaedu.vkarma.utility.STUDENT_KEY
import com.vkarmaedu.vkarma.utility.replaceFragmentAddToBackStack
import kotlinx.android.synthetic.main.fragment_student_list.view.*

class StudentListFragment : Fragment(), StudentListAdapter.OnItemClickListener {

    private val myAdapter = StudentListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_student_list, container, false)

        root.recycler_view.apply{
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val list = ArrayList<Student>()
        for (i in 0..10) list.add(Student("", i, "Rahul modi", "XII - *", "", "", "", "", "", ""))
        myAdapter.updateData(list)

        return root
    }

    override fun onItemClickListener(student: Student) {
        val fragment = TeacherMyStudentFragment()
        val bundle = Bundle()
        bundle.putParcelable(STUDENT_KEY, student)
        fragment.arguments = bundle
        replaceFragmentAddToBackStack(activity, fragment)
    }
}
