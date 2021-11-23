package com.example.papb_team5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.papb_team5.adapter.TaskItemAdapter
import com.example.papb_team5.adapter.toDoItemAdapter
import com.example.papb_team5.application.TasksApplication
import com.example.papb_team5.data.datasource
import com.example.papb_team5.data_entity.Task
import com.example.papb_team5.view_model.TaskViewModel
import com.example.papb_team5.view_model.TaskViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val newTaskActivityRequestCode = 1
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as TasksApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDataset = datasource().loadAffirmations()

        val recyclerView = findViewById<RecyclerView>(R.id.todo_recycler)
        val adapter = toDoItemAdapter(this, myDataset)
        //val adapter = TaskItemAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView?.setHasFixedSize(true)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewTaskActivity::class.java)
            startActivityForResult(intent, newTaskActivityRequestCode)
        }

        /*
        taskViewModel.allTasks.observe(this) { tasks ->
            // Update the cached copy of the words in the adapter.
            tasks.let { adapter.submitList(it) }
        }*/



        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                //R.id.home->setCurrentFragment(mainFragment)

                /*
                R.id.home-> {
                    val intent = Intent(this@MainActivity, fragment_home::class.java)
                    startActivity(intent)
                }
                */

                //.id.calendar->setCurrentFragment()
                //R.id.profile->setCurrentFragment(profileFragment)
                R.id.profile->{
                    val intent = Intent(this@MainActivity, profileActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newTaskActivityRequestCode && resultCode == Activity.RESULT_OK) {

            val replyTitle = intentData?.getStringExtra(NewTaskActivity.EXTRA_REPLY1).toString()
            val replyDesc = intentData?.getStringExtra(NewTaskActivity.EXTRA_REPLY2).toString()

            let {
                val task = Task(replyTitle, replyDesc)
                taskViewModel.insert(task)
            }

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}