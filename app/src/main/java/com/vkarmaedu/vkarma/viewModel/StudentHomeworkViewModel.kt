package com.vkarmaedu.vkarma.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.vkarmaedu.vkarma.data.UserRepo.batch
import com.vkarmaedu.vkarma.data.UserRepo.institute
import com.vkarmaedu.vkarma.dataModels.Homework

class StudentHomeworkViewModel : ViewModel() {
    var listSize : MutableLiveData<Int> = MutableLiveData(0)
    private val list = ArrayList<Homework>()

    private val databaseRef = FirebaseDatabase.getInstance().getReference("Institute/$institute/$batch/homework")
    private val listener = object : ChildEventListener{
        override fun onCancelled(p0: DatabaseError) {
        }
        override fun onChildMoved(p0: DataSnapshot, p1: String?) {
        }
        override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        }
        override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
            val homework : Homework? = snapshot.getValue(Homework::class.java)
            if (homework != null){
                list.add(homework)
                listSize.value = list.size
            }
        }
        override fun onChildRemoved(p0: DataSnapshot) {
        }
    }

    init {
        databaseRef.addChildEventListener(listener)
    }

    fun getList() = list

    fun getListSortedByDate() = list.sortedBy { it.date }.reversed()

    override fun onCleared() {
        super.onCleared()
        databaseRef.removeEventListener(listener)
    }

    companion object {
        private const val TAG = "StudentHomeworkViewmodel"
    }
}