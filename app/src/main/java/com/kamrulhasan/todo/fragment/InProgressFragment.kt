package com.kamrulhasan.todo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kamrulhasan.todo.R
import com.kamrulhasan.todo.adapter.InProgressAdapter
import com.kamrulhasan.todo.model.DoneItem
import com.kamrulhasan.todo.model.InprogressItem
import com.kamrulhasan.todo.model.ToDoItem
import com.kamrulhasan.todo.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_in_progress.*
import kotlinx.android.synthetic.main.fragment_in_progress.tv_no_value
import kotlinx.android.synthetic.main.fragment_to_do_list.*

class InProgressFragment : Fragment() {

    private lateinit var viewModel : ToDoViewModel
    private lateinit var adapter: InProgressAdapter
    private var inProgressList = listOf<InprogressItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Assigning late_init var
        viewModel = ViewModelProvider(this)[ToDoViewModel::class.java]
        adapter = InProgressAdapter(requireContext(),viewModel)

        //assigning recycler_view adapter and layout
        recycler_view_in_progress.adapter = adapter
        recycler_view_in_progress.layoutManager = LinearLayoutManager(requireContext())

        //observing live data
        viewModel.readAllInProgress.observe(viewLifecycleOwner){
            inProgressList = it
            adapter.setData(it)
            getEmptyMassage()
            onSwipeDelete()
            onSwipeRight()
        }
    }

    //showing empty massage if the list is Empty
    private fun getEmptyMassage() {
        if(inProgressList.isEmpty()){
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
                val ipItem = inProgressList[position]

                //removing from in progress list
                viewModel.removeItem(ipItem)
                adapter.notifyDataSetChanged()

                val item = ToDoItem(
                    ipItem.id,
                    ipItem.title,
                    ipItem.description,
                    ipItem.date
                )

                //adding todo_list
                viewModel.addItem(item)
                Toast.makeText(
                    context,
                    "One item deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }).attachToRecyclerView(recycler_view_in_progress)
    }
    private fun onSwipeRight() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

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
                val ipItem = inProgressList[position]

                //removing from in progress list
                viewModel.removeItem(ipItem)
                adapter.notifyDataSetChanged()

                val item = DoneItem(
                    ipItem.id,
                    ipItem.title,
                    ipItem.description,
                    ipItem.date
                )

                //adding todo_list
                viewModel.addItem(item)
                Toast.makeText(
                    context,
                    "One item deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }).attachToRecyclerView(recycler_view_in_progress)
    }
}