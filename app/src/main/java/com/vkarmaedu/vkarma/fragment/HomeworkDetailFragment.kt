package com.vkarmaedu.vkarma.fragment

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.firebase.storage.FirebaseStorage
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.dataModels.Homework
import com.vkarmaedu.vkarma.utility.dateFormat
import com.vkarmaedu.vkarma.utility.showSnack
import kotlinx.android.synthetic.main.fragment_homework_detail.view.*

class HomeworkDetailFragment : Fragment() {

    private val homework by lazy { arguments?.getParcelable<Homework>(HOMEwORK_KEY) }
    private val storageRef by lazy { FirebaseStorage.getInstance().getReference("homework_attachment") }
    private val downloadManager : DownloadManager by lazy { activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_homework_detail, container, false)

        homework?.let {
            root.subject.text = it.subName
            root.teacher.text = it.teachName
            root.content.text = it.text
            root.date.text = dateFormat.format(it.date)
            if (it.attachment == null){
                root.attachment.visibility = View.GONE
            }
        }

        root.attachment.setOnClickListener {
            requestPermission()
        }

        return root
    }

    private fun downloadFile(url : String){
        val request = DownloadManager.Request(Uri.parse(url))
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
        downloadManager.enqueue(request)
    }

    private fun requestPermission(){
        if (context?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.WRITE_EXTERNAL_STORAGE) }
            != PackageManager.PERMISSION_GRANTED){
            activity?.let { ActivityCompat.requestPermissions(it, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE) , REQUEST_WRITE_STORAGE_PERMISSION) }
        }else{
            homework?.attachment?.let { downloadFile(it) }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
             REQUEST_WRITE_STORAGE_PERMISSION -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    homework?.attachment?.let { downloadFile(it) }
                 else {
                    showSnack(this.requireView(), "Permission Denied by user")
                    requestPermission()
                }
            }
        }
    }

    companion object {
        private const val HOMEwORK_KEY = "homework_key"
        private const val REQUEST_WRITE_STORAGE_PERMISSION = 4
    }
}
