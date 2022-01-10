package com.androidStudyJams.record_attendance.add2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidStudyJams.record_attendance.R

class abAdapter(
    val context:Context,
    val list: ArrayList<numberData>
) :RecyclerView.Adapter<abAdapter.AbHolder>() {



    lateinit var temp:ArrayList<numberData>

    init {
        temp = list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.ab_item,parent,false)

        return AbHolder(view)
    }

    override fun getItemCount(): Int {
        return temp.size
    }

    override fun onBindViewHolder(holder: AbHolder, position: Int) {


        holder.textv.text = temp[position].rollno.toString()
        if (temp[position].isSelected == true)
        {
            holder.textv.setBackgroundColor(Color.DKGRAY)
            holder.textv.setTextColor(Color.WHITE)
        }
        else
        {
            holder.textv.setBackgroundColor(Color.GREEN)
            holder.textv.setTextColor(Color.BLACK)
        }

        holder.textv.setOnClickListener {

            val tv = it.findViewById<TextView>(R.id.ab_tem_tv)
            val index = tv.text.toString().toInt() - 1

            if(temp[index].isSelected)
            {
                temp[index].isSelected = false
                notifyItemChanged(index)
            }
            else{
                temp[index].isSelected = true
                notifyItemChanged(index)
            }
        }
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