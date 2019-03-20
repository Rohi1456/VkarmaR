package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.viewModel.StudentProfileViewModel

class ProfileDetailFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(StudentProfileViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile_detail, container, false)

        return root
    }


}
