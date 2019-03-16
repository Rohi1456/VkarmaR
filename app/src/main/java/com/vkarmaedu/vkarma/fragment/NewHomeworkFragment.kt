package com.vkarmaedu.vkarma.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.data.UserRepo
import com.vkarmaedu.vkarma.dataModels.Homework
import com.vkarmaedu.vkarma.viewModel.NewHomeworkViewModel
import kotlinx.android.synthetic.main.fragment_new_homework.view.*

class NewHomeworkFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(NewHomeworkViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_new_homework, container, false)

        val colors = arrayOf("Mathematics", "Physics", "Chemistry", "Biology", "English", "Physical", "Computer Science")
        val adapter = context?.let{ArrayAdapter(it, android.R.layout.simple_spinner_item, colors)}
        adapter?.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        root.class_spinner.adapter = adapter
        root.subject_spinner.adapter = adapter

        root.attachment.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            startActivityForResult(Intent.createChooser(intent, "Select File"), ATTACHMENT)
        }

        root.submit.setOnClickListener {
            val homework = Homework(
                root.subject_spinner.selectedItem.toString(),
                UserRepo.name, UserRepo.batch,
                System.currentTimeMillis(),
                root.editText.text.toString(),
                ""
            )
            viewModel.insertFirebase(homework)

        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ATTACHMENT && resultCode == Activity.RESULT_OK && data != null) {
            data.data?.let {
                viewModel.uri = it
            }
        }
    }

    companion object {
        private const val ATTACHMENT = 1
    }
}