package com.vkarmaedu.vkarma.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.vkarmaedu.vkarma.data.UserRepo.UId
import com.vkarmaedu.vkarma.data.UserRepo.batch
import com.vkarmaedu.vkarma.dataModels.Remarks
import kotlinx.coroutines.runBlocking

class StudentRemarkViewModel : ViewModel() {
    private val databaseRef by lazy { FirebaseDatabase.getInstance().getReference("institute/1/$batch/remarks") }
    private val listRemarks = ArrayList<Remarks>()

    private val listener = object : ChildEventListener{
        override fun onCancelled(p0: DatabaseError) {
        }
        override fun onChildMoved(p0: DataSnapshot, p1: String?) {
        }
        override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        }
        override fun onChildAdded(snapShot: DataSnapshot, p1: String?) {
            val rem = snapShot.getValue(Remarks::class.java)
            if (rem != null) listRemarks.add(rem)
        }
        override fun onChildRemoved(p0: DataSnapshot) {
        }
    }

    fun getRemarks() = listRemarks

    fun getRemarksBySub(sub : String) = listRemarks.filter { it.subName == sub }

    fun fetchRemarks() = runBlocking {
        databaseRef.equalTo(UId).addChildEventListener(listener)
    }

    override fun onCleared() {
        super.onCleared()
        databaseRef.removeEventListener(listener)
    }

    companion object {
        private const val TAG = "StudentRemarkViewmodel"
    }
}