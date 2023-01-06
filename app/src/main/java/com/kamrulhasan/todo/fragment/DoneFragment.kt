package com.kamrulhasan.todo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kamrulhasan.todo.R
import com.kamrulhasan.todo.adapter.DoneAdapter
import com.kamrulhasan.todo.model.DoneItem
import com.kamrulhasan.todo.model.InprogressItem
import com.kamrulhasan.todo.model.ToDoItem
import com.kamrulhasan.todo.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_done.*
import kotlinx.android.synthetic.main.fragment_done.tv_no_value
import kotlinx.android.synthetic.main.fragment_in_progress.*

class DoneFragment : Fragment() {

    private lateinit var adapter: DoneAdapter
    private lateinit var viewModel: ToDoViewModel
    private var doneList = listOf<DoneItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_done, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //assigning late_init var
        viewModel = ViewModelProvider(this)[ToDoViewModel::class.java]
        adapter = DoneAdapter(requireContext(),viewModel)

        //assigning recycler_view adapter and layout
        recycler_view_done.adapter = adapter
        recycler_view_done.layoutManager = LinearLayoutManager(requireContext())

        //observing live data
        viewModel.readAllDoneList.observe(viewLifecycleOwner) {
            doneList = it
            adapter.setData(it)
            getEmptyMassage()
            adapter.notifyDataSetChanged()
            onSwipeDelete()
        }


    }

    //showing empty massage if the list is Empty
    private fun getEmptyMassage() {
        if(doneList.isEmpty()){
            tv_no_value.visibility = View.VISIBLE
        }else{
            tv_no_value.visibility = View.GONE
        }
    }

    private fun onSwipeDelete() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //get the item
                val position = viewHolder.adapterPosition
                val doneItem = doneList[position]

                //removing from in progress list
                viewModel.removeItem(doneItem)
                adapter.notifyDataSetChanged()

                val item = InprogressItem(
                    doneItem.id,
                    doneItem.title,
                    doneItem.description,
                    doneItem.date
                )

                //adding in_progress_list
                viewModel.addItem(item)
                Toast.makeText(
                    context,
                    "One item deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }).attachToRecyclerView(recycler_view_done)
    }

}