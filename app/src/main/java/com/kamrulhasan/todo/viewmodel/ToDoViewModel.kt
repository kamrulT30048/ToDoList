package com.kamrulhasan.todo.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.kamrulhasan.todo.model.DoneItem
import com.kamrulhasan.todo.model.InprogressItem
import com.kamrulhasan.todo.model.ToDoItem
import com.kamrulhasan.todo.model.ToDoItem_DataBase
import com.kamrulhasan.todo.repository.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "ToDoViewModel"
    val readAllToDoList: LiveData<List<ToDoItem>>
    val readAllInProgress: LiveData<List<InprogressItem>>
    val readAllDoneList: LiveData<List<DoneItem>>
    val repository: ItemRepository

    init {
        val itemDao = ToDoItem_DataBase.getDatabase(application).getItemDao()
        repository = ItemRepository(itemDao)
        readAllToDoList = repository.readAllToDoList
        readAllInProgress = repository.readAllInProgress
        readAllDoneList = repository.readAllDoneList
    }

    fun addItem(toDoItem: ToDoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(toDoItem)
        }
    }

    fun addItem(inprogressItem: InprogressItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(inprogressItem)
        }
    }

    fun addItem(doneItem: DoneItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(doneItem)
        }
    }

    fun removeItem(toDoItem: ToDoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeItem(toDoItem)
        }
    }

    fun removeItem(inprogressItem: InprogressItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeItem(inprogressItem)
        }
    }

    fun removeItem(doneItem: DoneItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeItem(doneItem)
        }
    }

    fun updateItem(toDoItem: ToDoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItem(toDoItem)
        }
    }

    fun updateItem(doneItem: DoneItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItem(doneItem)
        }
    }

    fun updateItem(inprogressItem: InprogressItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItem(inprogressItem)
        }
    }
}
