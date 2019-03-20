package com.vkarmaedu.vkarma.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.vkarmaedu.vkarma.fragment.ProfileDetailFragment
import com.vkarmaedu.vkarma.fragment.RemarksFragment
import com.vkarmaedu.vkarma.fragment.StudentResultsFragment

class TeacherStudentPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> ProfileDetailFragment()
            1 -> StudentResultsFragment()
            else -> RemarksFragment()
        }
    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "DETAILS"
            1 -> "RESULT"
            else -> "REMARKS"
        }
    }
}