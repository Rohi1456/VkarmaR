package com.vkarmaedu.vkarma.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.vkarmaedu.vkarma.data.UserRepo.UId
import com.vkarmaedu.vkarma.data.UserRepo.batch
import com.vkarmaedu.vkarma.dataModels.Results
import kotlinx.coroutines.runBlocking

class StudentResultViewModel : ViewModel() {
    private val databaseRef by lazy { FirebaseDatabase.getInstance().getReference("institute/1/$batch/results") }
    private val resultsList = ArrayList<Results>()

    val listener = object : ChildEventListener {
        override fun onCancelled(p0: DatabaseError) {
        }
        override fun onChildMoved(p0: DataSnapshot, p1: String?) {
        }
        override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        }
        override fun onChildAdded(snapShot: DataSnapshot, p1: String?) {
            val res = snapShot.getValue(Results::class.java)
            if (res != null) resultsList.add(res)
        }
        override fun onChildRemoved(p0: DataSnapshot) {
        }
    }

    fun getResults() = resultsList

    fun getResultsByTest(test : String) = resultsList.filter { it.testName == test }

    fun getResultBySubject(subject : String) = resultsList.filter { it.subject == subject }

    fun getAverageScoreOfSubject(subject : String) : Float{
        val list = getResultBySubject(subject)
        var counter = 0f
        for (res in list){
            counter += (res.obtained / res.total) * 100
        }
        return counter
    }

    fun fetchResult() = runBlocking {
        databaseRef.equalTo(UId).addChildEventListener(listener)
    }

    override fun onCleared() {
        super.onCleared()
        databaseRef.removeEventListener(listener)
    }
}