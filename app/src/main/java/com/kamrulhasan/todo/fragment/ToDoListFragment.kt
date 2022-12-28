package com.kamrulhasan.todo.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kamrulhasan.todo.R
import com.kamrulhasan.todo.adapter.ToDoAdapter
import com.kamrulhasan.todo.data.ToDoItem
import com.kamrulhasan.todo.viewmodel.ToDoViewModel
import com.kamrulhasan.todo.viewmodel.ToDoViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_to_do_list.*

class ToDoListFragment : Fragment() {
    private lateinit var viewModel : ToDoViewModel
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

        val factory = ToDoViewModelProviderFactory()
        viewModel = ViewModelProvider(requireActivity(), factory)[ToDoViewModel::class.java]
        initializeAdapter()

    }

    private fun initializeAdapter() {
        recycler_view_todo.layoutManager = LinearLayoutManager(requireContext())
        observeData()
    }

    private fun observeData() {
        viewModel.list.observe(viewLifecycleOwner) {
            recycler_view_todo.adapter = ToDoAdapter(requireContext(), viewModel, it)
            Log.d(TAG, "observer called"+viewModel.newList.size)

        }
    }
}