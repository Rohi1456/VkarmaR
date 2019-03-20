package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkarmaedu.vkarma.adapters.TeacherResultsAdapter
import com.vkarmaedu.vkarma.dataModels.TeacherResult
import kotlinx.android.synthetic.main.fragment_teacher_results.view.*

public class TeacherResultsFragment : Fragment(),TeacherResultsAdapter.OnItemClickListener

{

    private val myAdapter = TeacherResultsAdapter(this)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(com.vkarmaedu.vkarma.R.layout.fragment_teacher_results, container, false)
        val list = ArrayList<TeacherResult>()
        for (i in 0..30) {
            list.add(TeacherResult("103","Jhon","99","100"))
        }
        myAdapter.changeData(list)
        root.uploadResultsRV.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = myAdapter
        }
        return root
    }

    override fun onItemClickListener(position: Int, itemView: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}