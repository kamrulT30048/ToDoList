package com.kamrulhasan.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kamrulhasan.todo.adapter.ToDoAdapter
import com.kamrulhasan.todo.data.ToDoItem
import com.kamrulhasan.todo.fragment.AddItemFragmentDirections
import com.kamrulhasan.todo.fragment.ToDoListFragment
import com.kamrulhasan.todo.fragment.ToDoListFragmentDirections
import com.kamrulhasan.todo.viewmodel.ToDoViewModel
import com.kamrulhasan.todo.viewmodel.ToDoViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_to_do_list.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    //adding menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        return super.onCreateOptionsMenu(menu)
    }

    //operation on item select
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.todo_add_menu_icon ->{
                navController.navigate(R.id.addItemFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}