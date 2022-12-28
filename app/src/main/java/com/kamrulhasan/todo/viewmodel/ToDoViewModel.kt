package com.kamrulhasan.todo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamrulhasan.todo.data.ToDoItem

class ToDoViewModel : ViewModel()  {
    private val TAG = "ToDoViewModel"
        val list = MutableLiveData<ArrayList<ToDoItem>>()
        val newList = arrayListOf<ToDoItem>()

        fun addItem(toDoItem: ToDoItem){
            Log.d(TAG, "addItem: ${newList.size}")
            newList.add(toDoItem)
            Log.d(TAG, "addItem: ${newList.size}")

            list.value = newList

        }

        fun removeItem(toDoItem: ToDoItem){
            newList.remove(toDoItem)
            list.value = newList
        }
        fun removeItem(position: Int){
            newList.removeAt(position)
            list.value = newList
        }
}