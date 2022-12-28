package com.kamrulhasan.todo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ToDoItem(
    val title : String,
    val description: String,
    val date: String
) : Parcelable
