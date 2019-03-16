package com.vkarmaedu.vkarma.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.vkarmaedu.vkarma.data.UserRepo.batch
import com.vkarmaedu.vkarma.data.UserRepo.UId
import com.vkarmaedu.vkarma.dataModels.Student
import kotlinx.coroutines.runBlocking

class StudentProfileViewModel : ViewModel() {
    private val databaseRef by lazy { FirebaseDatabase.getInstance().getReference("institute/1/$batch/students/$UId") }
    private var student : Student? = null

    private val listener = object : ValueEventListener{
        override fun onCancelled(p0: DatabaseError) {
        }
        override fun onDataChange(snapshot: DataSnapshot) {
            student = snapshot.getValue(Student::class.java)
        }
    }

    fun getStudentDetail() : Student{
        if (student == null){
            fetchDetails()
        }
        return student ?: throw Exception("Something went wrong")
    }

    private fun fetchDetails() = runBlocking{
        databaseRef.addListenerForSingleValueEvent(listener)
    }

    override fun onCleared() {
        super.onCleared()
        databaseRef.removeEventListener(listener)
    }

    companion object {
        private const val TAG = "StudentProfileViewmodel"
    }
}
