package com.kamrulhasan.todo.fragment


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.kamrulhasan.todo.Constrain
import com.kamrulhasan.todo.R
import com.kamrulhasan.todo.adapter.ToDoAdapter
import com.kamrulhasan.todo.model.InprogressItem
import com.kamrulhasan.todo.model.ToDoItem
import com.kamrulhasan.todo.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_to_do_list.*
import java.text.SimpleDateFormat
import java.util.*

class ToDoListFragment : Fragment() {

    private lateinit var itemViewModel : ToDoViewModel
    private lateinit var adapter : ToDoAdapter
    // todoList data
    private var todoList  = listOf<ToDoItem>()
    private val TAG = "ToDoListFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //assigning late var
        itemViewModel = ViewModelProvider(this)[ToDoViewModel::class.java]
        adapter = ToDoAdapter(requireContext(),itemViewModel)

        //assigning recycler_view adapter and layout
        recycler_view_todo.adapter = adapter
        recycler_view_todo.layoutManager = LinearLayoutManager(requireContext())

        //observing todo_list live_data
        itemViewModel.readAllToDoList.observe(viewLifecycleOwner) {
            todoList = it
            adapter.setData(it)
            getEmptyMassage()
            onSwipeDelete()
        }

        //adding function call
        floating_add_button.setOnClickListener {
            addDialogCall()
        }
    }

    private fun onSwipeDelete() {
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

                val position = viewHolder.adapterPosition
                val item = todoList[position]

                //removing the item from todo_list
                itemViewModel.removeItem(item)
                adapter.notifyDataSetChanged()

                val ipItem = InprogressItem(
                    item.id,
                    item.title,
                    item.description,
                    item.date
                )

                itemViewModel.addItem(ipItem)

                Toast.makeText(
                    context,
                    "One item deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }).attachToRecyclerView(recycler_view_todo)
    }

    private fun addDialogCall() {
        val builder = AlertDialog.Builder(context,R.style.MyDialogTheme)
        val inflater = LayoutInflater.from(context)
        val dialogLayout: View = inflater.inflate(R.layout.item_dialog_layout,null)
        val editTile = dialogLayout.findViewById<TextInputEditText>(R.id.et_dialog_title)
        val editDescripion = dialogLayout.findViewById<TextInputEditText>(R.id.et_dialog_description)

        with(builder){
            setTitle("Edit Task:")
            setPositiveButton("Add Task"){ dialog, which ->
                val task = ToDoItem(
                    0,
                    editTile.text.toString(),
                    editDescripion.text.toString(),
                    "Created: " + Constrain().getCurrentDateTime()
                )
                itemViewModel.addItem(task)
            }
            setNeutralButton("Cancel"){ dialog, which ->
                Toast.makeText(
                    context,
                    "Back to ToDo List",
                    Toast.LENGTH_SHORT
                ).show()
            }
            setView(dialogLayout)
            show()
        }
    }

    fun getEmptyMassage() {
        if(todoList.size == 0){
            tv_no_value.visibility = View.VISIBLE
        }else{
            tv_no_value.visibility = View.GONE
        }
    }
}