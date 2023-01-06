package com.kamrulhasan.todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "done_list_table")
data class DoneItem(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val date: String
)
