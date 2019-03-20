package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.TestAdapter
import kotlinx.android.synthetic.main.fragment_compare_result.view.*

class CompareResultFragment : Fragment() {

    private val myAdapter = TestAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_compare_result, container, false)

        root.recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter
        }


        return root
    }

}
