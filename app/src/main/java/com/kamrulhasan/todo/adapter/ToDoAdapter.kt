package com.kamrulhasan.todo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kamrulhasan.todo.R
import com.kamrulhasan.todo.data.ToDoItem
import com.kamrulhasan.todo.databinding.TodoItemBinding
import com.kamrulhasan.todo.viewmodel.ToDoViewModel

private const val TAG = "ToDoAdapter"
private lateinit var binding:TodoItemBinding
class ToDoAdapter (
    private val context: Context,
    private val viewModel: ToDoViewModel,
    private val arrayList: ArrayList<ToDoItem>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder( val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ToDoItem) {
            binding.todoItem = item
        }

    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ToDoViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDoItem = arrayList[position]
        holder.bind(toDoItem)
        Log.d(TAG, "onBindViewHolder: ${toDoItem.title}")


        // deleting the item
        holder.binding.cardDelete.setOnClickListener {
            viewModel.removeItem(toDoItem)
            Log.d(TAG, "onClick delete button ")
        }
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ${arrayList.size}")
        if (arrayList.size == 0) {
            Toast.makeText(context, "Blog list is empty", Toast.LENGTH_SHORT).show()
            
        }
        return arrayList.size
    }
}