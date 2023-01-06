package com.kamrulhasan.todo.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.graphics.toColor
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.kamrulhasan.todo.R
import com.kamrulhasan.todo.databinding.ActivityMainBinding
import com.kamrulhasan.todo.databinding.TodoItemBinding
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var navController:NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        window.statusBarColor = resources.getColor(R.color.action_bar)



        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        binding.btmNavBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.btm_nav_item_done -> {
                    navController.navigate(R.id.doneFragment)
                    true
                }
                R.id.btm_nav_item_in_progress -> {
                    navController.navigate(R.id.inProgressFragment)
                    true
                }
                else ->{
                    navController.navigate(R.id.toDoListFragment)
                    true
                }

            }


        }
//        binding.btmNavBar.se

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    //adding menu
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_item,menu)
//        return super.onCreateOptionsMenu(menu)
//    }

//    //operation on item select
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.todo_add_menu_icon ->{
//                navController.navigate(R.id.addItemFragment)
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}