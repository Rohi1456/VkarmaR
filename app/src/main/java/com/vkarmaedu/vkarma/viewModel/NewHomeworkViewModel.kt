package com.vkarmaedu.vkarma.viewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.vkarmaedu.vkarma.data.UserRepo.institute
import com.vkarmaedu.vkarma.data.UserRepo.batch
import com.vkarmaedu.vkarma.dataModels.Homework

class NewHomeworkViewModel : ViewModel() {
    private val storageRef by lazy { FirebaseStorage.getInstance().getReference("Homework") }
    private val databaseRef = FirebaseDatabase.getInstance().getReference("institute/$institute/$batch/homework")
    private var downloadUri = ""
    var uri: Uri? = null

    fun insertFirebase(homework: Homework) {
        uri?.let { insertStorage(it) }
        homework.attachment = downloadUri
        databaseRef.push().setValue(homework)
    }

    private fun insertStorage(uri: Uri) {
        val fileReference = storageRef.child("${System.currentTimeMillis()}")
        val uploadTask = fileReference.putFile(uri)

        uploadTask.continueWithTask {
            if (!it.isSuccessful) {
                it.exception?.let {
                    throw it
                }
            }
            fileReference.downloadUrl
        }.addOnCompleteListener {
            if (it.isSuccessful) {
                downloadUri = it.result.toString()
            } else {
                Log.d(TAG, "upload failed : ${it.exception}")
            }
        }
    }

    companion object {
        private const val TAG = "NewHomeworkViewmodel"
    }
}