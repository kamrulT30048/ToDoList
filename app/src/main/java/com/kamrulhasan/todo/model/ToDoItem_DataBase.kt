package com.kamrulhasan.todo.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoItem::class, InprogressItem::class, DoneItem::class], version = 3, exportSchema = false)
abstract class ToDoItem_DataBase : RoomDatabase() {

    abstract fun getItemDao(): ToDo_Dao

    companion object{
        @Volatile
        private var INSTANCE: ToDoItem_DataBase? = null

        fun getDatabase(context: Context) : ToDoItem_DataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoItem_DataBase::class.java,
                    "todo_item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return  instance
            }
        }

    }
}