package com.vkarmaedu.vkarma.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.vkarmaedu.vkarma.data.UserRepo
import com.vkarmaedu.vkarma.data.UserRepo.UId
import com.vkarmaedu.vkarma.data.UserRepo.batch
import com.vkarmaedu.vkarma.dataModels.Attendance

class StudentAttendanceViewModel : ViewModel() {
    private val attendanceDetailRef by lazy {
        FirebaseDatabase.getInstance().getReference("institute/${UserRepo.institute}/$batch/attendance")
    }
    val listAttendance = ArrayList<Attendance>()
    var present = 0

    val percentage by lazy {
        present * 100 / getTotal()
    }

    private val listener = object : ChildEventListener {
        override fun onCancelled(p0: DatabaseError) {
        }
        override fun onChildMoved(p0: DataSnapshot, p1: String?) {
        }
        override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        }
        override fun onChildAdded(p0: DataSnapshot, p1: String?) {

            val at = p0.getValue(Attendance::class.java)
            if (at != null) {
                if (at.present) present++
                listAttendance.add(at)
            }
        }
        override fun onChildRemoved(p0: DataSnapshot) {
        }
    }

    fun getItem(position: Int) = listAttendance[position]

    fun getTotal() = if (listAttendance.size == 0) 1 else listAttendance.size

    fun fetchData(){
        attendanceDetailRef.orderByChild("today/UId").equalTo(UId).addChildEventListener(listener)
    }

    override fun onCleared() {
        super.onCleared()
    }

    companion object {
        private const val TAG = "StudentAttendanceViewModel"
    }
}