package com.vkarmaedu.vkarma.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.viewModel.StudentAttendanceViewModel
import kotlinx.android.synthetic.main.fragment_profile_atten.view.*

class ProfileAttenFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(StudentAttendanceViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile_atten, container, false)
        viewModel.fetchData()
        root.progressBar.progress = viewModel.percentage
        root.noPresent.text = viewModel.present.toString()
        root.noAbsent.text = "${viewModel.getTotal() - viewModel.present}"
        root.percentage.text = viewModel.percentage.toString()

        root.graph.setBackgroundColor(Color.WHITE)
        root.graph.setNoDataText("No Record Found")
        root.graph.setDrawGridBackground(true)
        root.graph.setDrawBorders(true)
        root.graph.setBorderColor(Color.BLACK)
        root.graph.setBorderWidth(2f)

        return root
    }


}
