package com.vkarmaedu.vkarma.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.dataModels.TeacherResult
import com.vkarmaedu.vkarma.fragment.TeacherResultsFragment
import kotlinx.android.synthetic.main.list_teacher_results.view.*

class TeacherResultsAdapter(private val listener: TeacherResultsFragment) : RecyclerView.Adapter<TeacherResultsAdapter.MyViewHolder>()  {


    private var list : List<TeacherResult> = emptyList()


    fun changeData(list : List<TeacherResult>){
        this.list = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return TeacherResultsAdapter.MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_teacher_results, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    public interface OnItemClickListener{
        fun onItemClickListener(position: Int, itemView: View)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val teacherResult = list[position]
        if((position%2)!=0)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#0d9ec3ff"))
        }
        holder.id.text = teacherResult.id
        holder.name.text = teacherResult.name
        holder.itemView.setOnClickListener { listener.onItemClickListener(position,holder.itemView) }
    }

    class MyViewHolder(val item : View) : RecyclerView.ViewHolder(item) {
        val id  : TextView = item.idTV
        val name : TextView = item.nameTV
        val marks: EditText = item.marksET
        val totalMarks : EditText = item.totalET
    }
}

private fun TeacherResult.onItemClickListener(position: Int, itemView: View) {

}
