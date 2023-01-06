package com.kamrulhasan.todo.adapter

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
import com.kamrulhasan.todo.model.InprogressItem
import com.kamrulhasan.todo.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.todo_item.view.*
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "InProgressAdapter"
class InProgressAdapter( private val context: Context, private val viewModel: ToDoViewModel)
    : RecyclerView.Adapter<InProgressAdapter.InProgressViewHolder>() {

    var itemList = emptyList<InprogressItem>()


    class InProgressViewHolder(val binding: View)
        : RecyclerView.ViewHolder(binding.rootView){

//        val delete = binding.findViewById<ImageView>(R.id.card_delete)
//        val edit = binding.findViewById<ImageView>(R.id.card_edit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InProgressViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return InProgressViewHolder(root)
    }

    override fun onBindViewHolder(holder: InProgressViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemView.card_title.text = item.title
//        title.text = item.title
        holder.itemView.card_description.text = item.description
        holder.itemView.card_date.text = item.date

        Log.d(TAG, "onBindViewHolder: ${item.title}")

        holder.itemView.item_card.setOnClickListener {
            editDialogCall(item)
//            Toast.makeText(context,"card clicked", Toast.LENGTH_SHORT).show()
        }

    }

    private fun editDialogCall(task: InprogressItem) {

        val builder = AlertDialog.Builder(context,R.style.MyDialogTheme)
        val inflater = LayoutInflater.from(context)
        val dialogLayout: View = inflater.inflate(R.layout.item_dialog_layout,null)
        val editTitle = dialogLayout.findViewById<TextInputEditText>(R.id.et_dialog_title)
        val editDescription = dialogLayout.findViewById<TextInputEditText>(R.id.et_dialog_description)

        editTitle.setText(task.title)
        editDescription.setText(task.description)

        with(builder){
            setTitle("Edit Task:")
            setPositiveButton("Update"){ dialog, which ->
                val taskInprogressItem = InprogressItem(
                    task.id,
                    editTitle.text.toString(),
                    editDescription.text.toString(),
                    "Last Update: " + Constrain().getCurrentDateTime()
                )
                viewModel.updateItem(taskInprogressItem)
            }
            setNeutralButton("Cancel"){ dialog, which ->
                Toast.makeText(
                    context,
                    "Back to InProgress List",
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

    fun setData(it: List<InprogressItem>?) {
        this.itemList = it!!
    }
}