package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.MyClassAdapter
import com.vkarmaedu.vkarma.utility.CLASS_KEY
import com.vkarmaedu.vkarma.utility.replaceFragmentAddToBackStack
import kotlinx.android.synthetic.main.fragment_my_class.view.*

class MyClassFragment : Fragment(), MyClassAdapter.OnItemClickListener{

    private val myAdapter = MyClassAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_my_class, container, false)
        root.recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = myAdapter
        }

        val list = ArrayList<String>()
        for (i in 0..10) list.add("Class XII - B")

        myAdapter.updateData(list)

        return root
    }

    override fun onItemClickListener(clas: String) {
        val fragment = StudentListFragment()
        val bundle = Bundle()
        bundle.putString(CLASS_KEY, clas)
        fragment.arguments = bundle
        replaceFragmentAddToBackStack(activity, fragment)
    }
}
