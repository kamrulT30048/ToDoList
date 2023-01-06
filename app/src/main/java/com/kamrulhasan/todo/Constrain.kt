package com.kamrulhasan.todo

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class Constrain {

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy/M/dd hh:mm")
        val today = dateFormat.format(Date())

        return today.toString()
    }

    companion object{

    }
}