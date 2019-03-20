package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.StudentResultAdapter
import com.vkarmaedu.vkarma.dataModels.Results
import com.vkarmaedu.vkarma.utility.replaceFragmentAddToBackStack
import com.vkarmaedu.vkarma.viewModel.StudentResultViewModel
import kotlinx.android.synthetic.main.fragment_student_results.view.*

class StudentResultsFragment : Fragment(), StudentResultAdapter.OnItemClickListener {

    private val viewModel by lazy { ViewModelProviders.of(this).get(StudentResultViewModel::class.java) }
    private val myAdapter = StudentResultAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_student_results, container, false)
        viewModel.fetchResult()
        root.recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter
        }

        val list = ArrayList<Results>()
        for (i in 0..10){
            list.add(Results("Test Name 1", "Chemistry", 70, 60))
        }
        myAdapter.updateData(list)

        root.compare.setOnClickListener {
            replaceFragmentAddToBackStack(activity, CompareResultFragment())
        }

        return root
    }

    override fun onItemClickListener(result: Results) {
        val fragment = ResultDetailFragment()
        replaceFragmentAddToBackStack(activity, fragment)
    }
}