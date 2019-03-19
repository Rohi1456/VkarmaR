package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.TeacherStudentPageAdapter
import kotlinx.android.synthetic.main.fragment_teacher_my_student.view.*

class TeacherMyStudentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_teacher_my_student, container, false)
        root.viewpager.adapter = activity?.supportFragmentManager?.let { TeacherStudentPageAdapter(it) }
        root.tabLayout.setupWithViewPager(root.viewpager)
        return root
    }


}
