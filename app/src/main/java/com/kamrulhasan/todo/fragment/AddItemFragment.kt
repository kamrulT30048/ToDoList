package com.kamrulhasan.todo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kamrulhasan.todo.R
import com.kamrulhasan.todo.data.ToDoItem
import com.kamrulhasan.todo.viewmodel.ToDoViewModel
import com.kamrulhasan.todo.viewmodel.ToDoViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_item.*
import kotlinx.android.synthetic.main.fragment_to_do_list.*
import java.text.SimpleDateFormat
import java.util.*


class AddItemFragment : Fragment() {

    private val TAG = "AddItemFragment"
    lateinit var itemData : ToDoItem

    //    lateinit var viewModel: ToDoViewModel
//
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    //    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add.setOnClickListener {

            if (et_title.text.isNotBlank() && et_description.text.isNotBlank()) {
                val itemTitle = et_title.text.toString()
                val itemDescription = et_description.text.toString()
                Log.d(TAG, "onViewCreated: $itemTitle")

                val factory = ToDoViewModelProviderFactory()
                val viewModel = ViewModelProvider(requireActivity(), factory)[ToDoViewModel::class.java]

                val item = ToDoItem(itemTitle, itemDescription, getCurrentDateTime())
//                itemData = item
                viewModel.addItem(item)

                //showing a toast
                Toast.makeText(
                    this.requireContext(),
                    "one item added",
                    Toast.LENGTH_SHORT
                ).show()

                Log.d(TAG, "onViewCreated: ${item.title}")
                findNavController().navigate(R.id.action_addItemFragment_to_toDoListFragment)

            } else {
                Toast.makeText(
                    this.requireContext(),
                    "Please fill the Form",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val today = dateFormat.format(Date())

        return today.toString()
    }
}