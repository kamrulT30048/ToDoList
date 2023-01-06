package com.kamrulhasan.todo.repository

import androidx.lifecycle.LiveData
import com.kamrulhasan.todo.model.DoneItem
import com.kamrulhasan.todo.model.InprogressItem
import com.kamrulhasan.todo.model.ToDoItem
import com.kamrulhasan.todo.model.ToDo_Dao

class ItemRepository(private val todoDao: ToDo_Dao) {
    val readAllToDoList: LiveData<List<ToDoItem>> = todoDao.readAllToDoList()
    val readAllInProgress: LiveData<List<InprogressItem>> = todoDao.readAllInProgress()
    val readAllDoneList: LiveData<List<DoneItem>> = todoDao.readAllDoneList()

    suspend fun addItem(toDoItem: ToDoItem) {
        todoDao.addToDoItem(toDoItem)
    }

    suspend fun addItem(inprogressItem: InprogressItem) {
        todoDao.addInProgressItem(inprogressItem)
    }

    suspend fun addItem(doneItem: DoneItem) {
        todoDao.addDoneItem(doneItem)
    }

    suspend fun removeItem(toDoItem: ToDoItem) {
        todoDao.removeItem(toDoItem)
    }

    suspend fun removeItem(inprogressItem: InprogressItem) {
        todoDao.removeItem(inprogressItem)
    }

    suspend fun removeItem(doneItem: DoneItem) {
        todoDao.removeItem(doneItem)
    }

    suspend fun updateItem(toDoItem: ToDoItem) {
        todoDao.updateItem(toDoItem)
    }

    suspend fun updateItem(doneItem: DoneItem) {
        todoDao.updateItem(doneItem)
    }

    suspend fun updateItem(inprogressItem: InprogressItem) {
        todoDao.updateItem(inprogressItem)
    }
}