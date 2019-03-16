package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.viewModel.StudentResultViewModel
import kotlinx.android.synthetic.main.fragment_student_results.view.*

class StudentResultsFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(StudentResultViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_student_results, container, false)
        viewModel.fetchResult()
        root.progressBar.visibility = View.GONE
        return root
    }
}
