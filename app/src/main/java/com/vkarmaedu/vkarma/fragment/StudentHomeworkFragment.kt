package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.HomeworkStudentAdapter
import com.vkarmaedu.vkarma.dataModels.Homework
import com.vkarmaedu.vkarma.utility.dateFormat
import com.vkarmaedu.vkarma.utility.replaceFragmentAddToBackStack
import com.vkarmaedu.vkarma.viewModel.StudentHomeworkViewModel
import kotlinx.android.synthetic.main.fragment_student_homework.*
import kotlinx.android.synthetic.main.fragment_student_homework.view.*

class StudentHomeworkFragment : Fragment() , HomeworkStudentAdapter.OnItemClickListener{

    private lateinit var myAdapter : HomeworkStudentAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(StudentHomeworkViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_student_homework, container, false)
        activity?.actionBar?.show()
        myAdapter = HomeworkStudentAdapter(this)

        root.homework_recycler_view.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@StudentHomeworkFragment.context)
        }
        root.today.visibility = View.GONE

        viewModel.listSize.observe(this, Observer {
            myAdapter.changeData(viewModel.getList())
            if (it > 0){
                visibilityNoHomework(false)
                root.today.visibility = getHomeworkToday(viewModel.getList()[0].date)
            }else{
                visibilityNoHomework(true)
            }
        })
        return root
    }

    private fun getHomeworkToday(date : Long): Int {
        return if (dateFormat.format(System.currentTimeMillis()) == dateFormat.format(date))
            View.VISIBLE
        else
            View.GONE
    }

    private fun visibilityNoHomework(bool : Boolean){
        homework_recycler_view.visibility = if (bool) View.GONE else View.VISIBLE
        homework_recycler_view.visibility = if (bool) View.VISIBLE else View.GONE
    }

    override fun onItemClickListener(homework: Homework) {
        val fragment = HomeworkDetailFragment()
        val bundle = Bundle()
        bundle.putParcelable(HOMEwORK_KEY, homework)
        replaceFragmentAddToBackStack(activity, fragment)
    }

    companion object {
        private const val HOMEwORK_KEY = "homework_key"
    }
}
