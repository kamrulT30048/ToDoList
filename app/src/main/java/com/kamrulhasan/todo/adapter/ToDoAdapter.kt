package com.kamrulhasan.todo.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.kamrulhasan.todo.Constrain
import com.kamrulhasan.todo.R
import com.kamrulhasan.todo.model.ToDoItem
import com.kamrulhasan.todo.databinding.TodoItemBinding
import com.kamrulhasan.todo.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.todo_item.view.*
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "ToDoAdapter"

class ToDoAdapter (
    private val context: Context,
    private val itemViewModel: ToDoViewModel
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    var itemList = emptyList<ToDoItem>()

    class ToDoViewHolder(private val binding: TodoItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ToDoItem) {
            binding.todoItem = item
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDoItem = itemList[position]
        holder.bind(toDoItem)
        holder.itemView.card_title.text = toDoItem.title
        Log.d(TAG, "onBindViewHolder: ${toDoItem.title}")

        holder.itemView.item_card.setOnClickListener {
            editDialogCall(toDoItem)
        }


    }

    private fun editDialogCall(todoTask: ToDoItem) {
        val builder = AlertDialog.Builder(context,R.style.MyDialogTheme)
        val inflater = LayoutInflater.from(context)
        val dialogLayout: View = inflater.inflate(R.layout.item_dialog_layout,null)
        val editTile = dialogLayout.findViewById<TextInputEditText>(R.id.et_dialog_title)
        val editDescripion = dialogLayout.findViewById<TextInputEditText>(R.id.et_dialog_description)

        editTile.setText(todoTask.title)
        editDescripion.setText(todoTask.description)

        with(builder){
            setTitle("Edit Task:")
            setPositiveButton("Update"){ dialog, which ->
                val task = ToDoItem(
                    todoTask.id,
                    editTile.text.toString(),
                    editDescripion.text.toString(),
                    "Last Update: " + Constrain().getCurrentDateTime()
                )
                itemViewModel.updateItem(task)
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


    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(it: List<ToDoItem>?) {
        if (it != null) {
            this.itemList = it
        }
        notifyDataSetChanged()
    }
}

