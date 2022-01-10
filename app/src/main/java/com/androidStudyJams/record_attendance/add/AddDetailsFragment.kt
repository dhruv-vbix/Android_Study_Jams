package com.androidStudyJams.record_attendance.add

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController

import com.androidStudyJams.record_attendance.R
import kotlinx.android.synthetic.main.add_details_fragment.*
import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.*

class AddDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = AddDetailsFragment()
    }

    private lateinit var viewModel: AddDetailsViewModel
    lateinit var subjectSpinner:Spinner
    lateinit var stdSpinner:Spinner
    lateinit var classSpinner:Spinner
    lateinit var date:TextView
    lateinit var total:EditText
    lateinit var note:EditText




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddDetailsViewModel::class.java)

        date = tv_date
        subjectSpinner = spinner_branch
        stdSpinner = spinner_sem
        classSpinner = spinner_lec
        total = et_total
        note = et_note
        lateinit var _date:String
        lateinit var _total:String
        lateinit var _note:String
        lateinit var _subject:String
        lateinit var _std:String
        lateinit var _class:String




        val date_ = SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().time)

        date.text ="Date : "+ date_

        //set date
        _date = date_

        val SubjectOptions = arrayOf("Select Subject","P","C","M","B")
        val StdOptions = arrayOf("Select Standard","6","7","8","9","10","11","12")
        val LectureOptions = arrayOf("Select Class","1","2","3","4","5")

        subjectSpinner.adapter = ArrayAdapter<String>(this.requireContext(),android.R.layout.simple_list_item_1,SubjectOptions)
        stdSpinner.adapter = ArrayAdapter<String>(this.requireContext(),android.R.layout.simple_list_item_1,StdOptions)
        classSpinner.adapter = ArrayAdapter<String>(this.requireContext(),android.R.layout.simple_list_item_1,LectureOptions)


        //branch on click

        subjectSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                _subject = SubjectOptions.get(position)
            }



        }


        //sem on click

        stdSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener

        {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                _std = StdOptions.get(position)
            }

        }


        //lecture on click

        classSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener
        {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                _class = LectureOptions.get(position)
            }

        }


       btn_save.setOnClickListener {

          _total = total.text.toString()
           _note = note.text.toString()

           try {

               val tempCon = _total.toInt()


           if (_subject =="Select Subject"  || _class == "Select Class" || _std =="Select Standard" || _total.isNullOrEmpty() )
           {
               Toast.makeText(this.requireContext(),"Please Select All Options",Toast.LENGTH_SHORT).show()
           }
           else
           {
               var action =  AddDetailsFragmentDirections.actionAddDetailsFragmentToAbsentPresentFragment(_date,_subject,_class,_std,_total,_note)
               it.findNavController().navigate(action)
           }
           }
           catch (e:NumberFormatException)
           {
               Toast.makeText(this.requireContext(),"Please Enter only number",Toast.LENGTH_SHORT).show()
           }
       }


    }

}
