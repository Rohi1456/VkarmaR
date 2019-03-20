package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.utility.replaceFragmentAddToBackStack
import com.vkarmaedu.vkarma.utility.showSnack
import kotlinx.android.synthetic.main.fragment_student.view.*
import kotlinx.android.synthetic.main.student_content.view.*

class TeacherFragment : Fragment() {

    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_teacher, container, false)

        val toggle = ActionBarDrawerToggle(
            activity, root.drawer_layout, root.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        root.drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        root.drawer_navigation.setNavigationItemSelectedListener (onDrawerItemSelectedListener)

        val navController = Navigation.findNavController(root.findViewById(R.id.teacher_container))
        root.findViewById<BottomNavigationView>(R.id.teacher_bottom_nav)
            .setupWithNavController(navController)

        root.notification.setOnClickListener {
            activity?.let { it1 -> replaceFragmentAddToBackStack(it1, NotificationFragment()) }
        }

        navController.addOnDestinationChangedListener{_, destination,_->
            root.toolbar.title = destination.label
        }
        return root
    }

    private val onDrawerItemSelectedListener = object : NavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            return when(item.itemId){
                R.id.fee_payment -> {
                    showSnack(this@TeacherFragment.requireView(), "fee")
                    true
                }
                R.id.logout -> {
                    auth.signOut()
                    true
                }
                else -> false
            }
        }
    }
}
