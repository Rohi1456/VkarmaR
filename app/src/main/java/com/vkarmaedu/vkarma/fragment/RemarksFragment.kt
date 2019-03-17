package com.vkarmaedu.vkarma.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.RemarksAdapter
import com.vkarmaedu.vkarma.dataModels.Remarks
import com.vkarmaedu.vkarma.utility.dateFormat
import kotlinx.android.synthetic.main.fragment_remarks.view.*
import kotlinx.android.synthetic.main.fragment_take_attendance.*
import kotlinx.android.synthetic.main.remarks_dialog.view.*

class RemarksFragment : Fragment() , RemarksAdapter.OnItemClickListener{

    val myAdapter = RemarksAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_remarks, container, false)

        root.recycler_view.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val list = ArrayList<Remarks>()
        for (i in 0 .. 10){
            list.add(Remarks("Science", "Rajiv", resources.getString(R.string.loremIpsum), System.currentTimeMillis()))
        }
        myAdapter.updateData(list)

        return root
    }
    override fun onItemClickListener(remark: Remarks) {
        val layout = layoutInflater.inflate(R.layout.remarks_dialog, teacher_card_container, false)
        layout.subject.text = remark.subName
        layout.teacher.text = remark.teachName
        layout.date.text = dateFormat.format(remark.date)
        layout.content.text = remark.content
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setView(layout)
        alertDialog.create()
        alertDialog.show()
    }
}
