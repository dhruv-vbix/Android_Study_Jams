package com.androidStudyJams.record_attendance.showitem

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidStudyJams.record_attendance.R
import com.androidStudyJams.record_attendance.database.attendanceData
import com.androidStudyJams.record_attendance.database.jsonFormatStore
import com.google.gson.Gson

class showAdapter(
    val context:Context,
    val item:attendanceData
) :RecyclerView.Adapter<showAdapter.AbHolder>() {



    lateinit var temp:attendanceData
    lateinit var totalArr:ArrayList<Int>

    init {
        temp = item
        val json = Gson()
        val data = json.fromJson(temp.absent,jsonFormatStore::class.java)
       totalArr = data.absent


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.ab_item,parent,false)

        return AbHolder(view)
    }

    override fun getItemCount(): Int {
        return totalArr.size
    }

    override fun onBindViewHolder(holder: AbHolder, position: Int) {


        holder.textv.setBackgroundColor(Color.DKGRAY)

        holder.textv.text = totalArr[position].toString()





    }

    inner class AbHolder(view: View):RecyclerView.ViewHolder(view)
    {
        fun bindText(position: Int) {
        }



        lateinit var textv:TextView

        init {
            textv = view.findViewById(R.id.ab_tem_tv)
        }
    }
}