package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.CompareResultsAdapter
import com.vkarmaedu.vkarma.adapters.TestAdapter
import com.vkarmaedu.vkarma.dataModels.Results
import kotlinx.android.synthetic.main.fragment_compare_result.view.*

class CompareResultFragment : Fragment() {

    private val myAdapter = CompareResultsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_compare_result, container, false)
        val list = ArrayList<Results>()
        for (i in 0..10){
            list.add(Results("Test Name 1", "PHYSICS", 100, 60))
        }
        root.recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
            adapter = myAdapter
        }
        myAdapter.updateData(list)
        return root
    }

}
