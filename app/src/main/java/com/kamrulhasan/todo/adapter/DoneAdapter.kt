package com.kamrulhasan.todo.adapter

import android.app.AlertDialog
import android.app.ProgressDialog.show
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
import com.kamrulhasan.todo.model.DoneItem
import com.kamrulhasan.todo.model.InprogressItem
import com.kamrulhasan.todo.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.todo_item.view.*
import java.nio.file.attribute.AclEntry.Builder

private const val TAG = "DoneAdapter"

class DoneAdapter(
    private val context: Context,
    private val viewModel: ToDoViewModel)
    :RecyclerView.Adapter<DoneAdapter.DoneTaskViewHolder>(){

    var doneTaskList = emptyList<DoneItem>()

    class DoneTaskViewHolder(val binding: View)
        :RecyclerView.ViewHolder(binding.rootView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneTaskViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent,false)
        return DoneTaskViewHolder(root)
    }

    override fun onBindViewHolder(holder: DoneTaskViewHolder, position: Int) {
        val item = doneTaskList[position]
        holder.itemView.card_title.text = item.title
        holder.itemView.card_description.text = item.description
        holder.itemView.card_date.text = item.date
        Log.d(TAG, "onBindViewHolder: ${item.title}")

        holder.itemView.item_card.setOnLongClickListener{
            deleteDialogCall(item)
            false
        }
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ${doneTaskList.size}")
        return doneTaskList.size
    }

    fun setData(it: List<DoneItem>?){
        this.doneTaskList = it!!
    }

    private fun deleteDialogCall(doneItem: DoneItem) {

        val builder = AlertDialog.Builder(context,R.style.MyDialogTheme)
        builder.setTitle("Androidly Alert")
        builder.setMessage("We have a message")

        builder.setNeutralButton("DELETE") { dialog, which ->
            viewModel.removeItem(doneItem)
            Toast.makeText(context,
                "Maybe", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}
