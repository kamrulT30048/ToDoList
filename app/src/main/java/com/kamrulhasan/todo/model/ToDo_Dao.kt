package com.kamrulhasan.todo.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDo_Dao {

    @Insert(onConflict = REPLACE)
    suspend fun addToDoItem(toDoItem: ToDoItem)

    @Insert(onConflict = REPLACE)
    suspend fun addDoneItem(doneItem: DoneItem)

    @Insert(onConflict = REPLACE)
    suspend fun addInProgressItem(inprogressItem: InprogressItem)

    @Query("SELECT * FROM todo_item_table ORDER BY title ASC")
    fun readAllToDoList(): LiveData<List<ToDoItem>>

    @Query("SELECT * FROM inprogress_item_table ORDER BY title ASC")
    fun readAllInProgress(): LiveData<List<InprogressItem>>

    @Query("SELECT * FROM done_list_table ORDER BY title ASC")
    fun readAllDoneList(): LiveData<List<DoneItem>>

    @Update
    suspend fun updateItem(toDoItem: ToDoItem)

    @Update
    suspend fun updateItem(inprogressItem: InprogressItem)

    @Update
    suspend fun updateItem(doneItem: DoneItem)

    @Delete
    suspend fun removeItem(toDoItem: ToDoItem)

    @Delete
    suspend fun removeItem(inprogressItem: InprogressItem)

    @Delete
    suspend fun removeItem(doneItem: DoneItem)

}