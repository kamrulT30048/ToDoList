package com.kamrulhasan.todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "todo_item_table")
data class ToDoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @NotNull val title : String,
    @NotNull val description: String,
    @NotNull val date: String
)
