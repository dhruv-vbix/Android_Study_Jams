package com.androidStudyJams.record_attendance.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.androidStudyJams.record_attendance.R
import com.androidStudyJams.record_attendance.database.attendanceData

class HomeAdapter(
    val context:Context,
    val list: List<attendanceData>
) :RecyclerView.Adapter<HomeAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_item,parent,false)

        return Holder(view)
    }

    override fun getItemCount(): Int {

      return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val listpos = list[position]

        holder.date.text = listpos.date
        holder.branch_sem.text = listpos.subject+"-"+listpos.std
        holder.lecture.text = "Lecture : "+listpos.`cls`
        holder.note.text = "Note : "+ listpos.note
        holder.root.setOnClickListener {

        val action =    HomeFragmentDirections.actionHomeFragmentToShowItemFragment(listpos.date,
                listpos.subject,
                listpos.`cls`,
                listpos.absent,
                listpos.total,
                listpos.std,
                listpos.note,
                listpos.id)

            it.findNavController().navigate(action)
        }

    }


    inner class Holder(view:View):RecyclerView.ViewHolder(view)
    {
        lateinit var date:TextView
        lateinit var branch_sem:TextView
        lateinit var lecture:TextView
        lateinit var note:TextView
        lateinit var root:LinearLayout
        init {
            date = view.findViewById(R.id.date_tv)
            branch_sem = view.findViewById(R.id.branch_sem_tv)
            lecture = view.findViewById(R.id.lecture_tv)
            note = view.findViewById(R.id.note_tv)
            root = view.findViewById(R.id.root_view_home)
        }




    }
}