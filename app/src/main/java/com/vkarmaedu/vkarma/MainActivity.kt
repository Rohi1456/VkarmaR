package com.vkarmaedu.vkarma

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.vkarmaedu.vkarma.data.UserRepo
import com.vkarmaedu.vkarma.fragment.ChooseFragment
import com.vkarmaedu.vkarma.fragment.LoginFragment
import com.vkarmaedu.vkarma.utility.popBackStack
import com.vkarmaedu.vkarma.utility.replaceFragment
import com.vkarmaedu.vkarma.utility.replaceFragmentAddToBackStack

class MainActivity : AppCompatActivity() {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private lateinit var authStateListener : FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        authStateListener = FirebaseAuth.AuthStateListener {
            val currentUser = it.currentUser
            if (currentUser == null){
                replaceFragmentAddToBackStack(this, LoginFragment())
            }
            else{
                currentUser.displayName?.let { it1 -> UserRepo.setCred(it1, currentUser.uid) }
            }
        }
        replaceFragment(this, ChooseFragment())
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            popBackStack(this)
        else
            super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        auth.addAuthStateListener (authStateListener)
    }

    override fun onPause() {
        super.onPause()
        auth.removeAuthStateListener (authStateListener)
    }

}
