package com.androidStudyJams.record_attendance.showitem

import android.content.DialogInterface
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.androidStudyJams.record_attendance.R
import com.androidStudyJams.record_attendance.database.Database
import com.androidStudyJams.record_attendance.database.attendanceData
import kotlinx.android.synthetic.main.show_item_fragment.*

class ShowItemFragment : Fragment() {

    companion object {
        fun newInstance() = ShowItemFragment()
    }

    private lateinit var viewModel: ShowItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.show_item_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ShowItemViewModel::class.java)

        val args = arguments?.let { ShowItemFragmentArgs.fromBundle(it) }
        val appdb = Database(this.requireContext()).getDao()

       val adapter = showAdapter(this.requireContext(), attendanceData(args!!.date,
           args.branch,
           args.sem,
           args.lecture,
           args.total,
           args.absent,
           args.note))

        recyclerView_show.layoutManager = GridLayoutManager(this.requireContext(),4)
        recyclerView_show.adapter = adapter

        tv_show.text = args?.branch+"-"+args?.sem+ " |  Lecture : "+args?.lecture +" |  "+args?.date +" | Total : "+args?.total

        btn_show.setOnClickListener {

            val alert = AlertDialog.Builder(this.requireContext())
            alert.setTitle("Are you sure ?")
            alert.setPositiveButton("Yes,delete it!",{ dialogInterface: DialogInterface, i: Int ->
                appdb.deleteById(args.id)
                Toast.makeText(this.requireContext(),"Deleted",Toast.LENGTH_SHORT).show()
                val action = ShowItemFragmentDirections.actionShowItemFragmentToHomeFragment()
                it.findNavController().navigate(action)

            })
            alert.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> })
            alert.show()



        }

        btn_share.setOnClickListener {
            val message = "Date : "+args.date + "\nBranch : "+args.branch +"\nSem : "+args.sem+"\nTotal : "+args.total +"\nLecture : "+args.lecture +"\nAbsent No : "+args.absent.drop(10).dropLast(1)

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,message)
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent,"Share to whatsapp "))
        }

    }

}
