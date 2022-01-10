package com.androidStudyJams.record_attendance.add2

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.androidStudyJams.record_attendance.R
import com.androidStudyJams.record_attendance.database.Database
import com.androidStudyJams.record_attendance.database.attendanceData
import com.androidStudyJams.record_attendance.database.jsonFormatStore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.absent_present_fragment.*

class AbsentPresentFragment : Fragment() {

    companion object {
        fun newInstance() = AbsentPresentFragment()
    }

    private lateinit var viewModel: AbsentPresentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.absent_present_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AbsentPresentViewModel::class.java)
        val appdb = Database(this.requireContext())
        val args = arguments?.let { AbsentPresentFragmentArgs.fromBundle(it) }

        tv_ab.text = args?.branch+"-"+args?.sem+ " | Lecture : "+args?.lecture +" | "+args?.date

        val list = ArrayList<numberData>()
        for(i in 1..args?.total?.toInt()!!)
        {
            list.add(numberData(i,false))
        }

        val adapter = abAdapter(this.requireContext(),list)
        recyclerView_ab.layoutManager = GridLayoutManager(this.requireContext(),4)
        recyclerView_ab.adapter = adapter


        btn_ab.setOnClickListener {
            val json = Gson()
            val temp = ArrayList<Int>()
            for (i in adapter.temp)
            {
                if (i.isSelected== true)
                {
                    temp.add(i.rollno)
                }
            }

            val jsonStr = json.toJson(jsonFormatStore(temp))
            val toclass = json.fromJson(jsonStr,jsonFormatStore::class.java)
            appdb.getDao().insertData(attendanceData(args.date,args.branch,args.sem,args.lecture,args.total,jsonStr,args.note))
            Toast.makeText(this.requireContext(),"Added",Toast.LENGTH_LONG).show()

            val action = AbsentPresentFragmentDirections.actionAbsentPresentFragmentToHomeFragment()

            it.findNavController().navigate(action)

        }

    }

}
