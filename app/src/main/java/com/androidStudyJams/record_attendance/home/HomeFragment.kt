package com.androidStudyJams.record_attendance.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.androidStudyJams.record_attendance.R
import com.androidStudyJams.record_attendance.database.Database
import com.androidStudyJams.record_attendance.database.attendanceData
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    lateinit var displayList:ArrayList<attendanceData>
    lateinit var list:List<attendanceData>
    lateinit var adapter:HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val appdb = Database(this.requireContext())
         list = appdb.getDao().getAllData()
        displayList = ArrayList<attendanceData>()
        displayList.clear()

        displayList.addAll(list)

        displayList.sortByDescending { it.date }


        adapter = HomeAdapter(this.requireContext(),displayList)
        home_frag_recyclerview.layoutManager = LinearLayoutManager(this.requireContext())
        home_frag_recyclerview.adapter= adapter

        floatingActionButton.setOnClickListener {

            val homeToAdd = HomeFragmentDirections.actionHomeFragmentToAddDetailsFragment()
            it.findNavController().navigate(homeToAdd)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu,menu)
        val searchItem = menu.findItem(R.id.menu_search)
        if (searchItem != null)
        {
            val searchView = searchItem.actionView as SearchView
            val et_search = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            et_search.hint = "Search by date (DD-MM-YYYY)"
            searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText!!.isNotEmpty())
                    {
                        displayList.clear()

                        val search = newText.toLowerCase()
                        list.forEach {
                            if (it.date.toLowerCase().contains(search))
                            {
                                displayList.add(it)
                            }
                        }
                        adapter.notifyDataSetChanged()

                    }
                    else{
                        displayList.clear()
                        displayList.addAll(list)
                        adapter.notifyDataSetChanged()
                    }
                    return true
                }

            })
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

}
