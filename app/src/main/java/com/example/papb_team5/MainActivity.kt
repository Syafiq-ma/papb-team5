package com.example.papb_team5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.papb_team5.adapter.toDoItemAdapter
import com.example.papb_team5.data_entity.Task
import com.example.papb_team5.room_database.Constant
import com.example.papb_team5.room_database.TaskRoomDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { TaskRoomDatabase(this) }
    lateinit var tasksAdapter: toDoItemAdapter

    /*
    private val newTaskActivityRequestCode = 1
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as TasksApplication).repository)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListener()
        setupRecyclerView()


        //val recyclerView = findViewById<RecyclerView>(R.id.todo_recycler)
        //val adapter = toDoItemAdapter(arrayListOf())
        //val adapter = TaskItemAdapter()
        //recyclerView.adapter = toDoItemAdapter(this, arrayListOf())
        //recyclerView.layoutManager = LinearLayoutManager(this)

        //recyclerView?.setHasFixedSize(true)

        /*
        taskViewModel.allTasks.observe(this) { tasks ->
            // Update the cached copy of the words in the adapter.
            tasks.let { adapter.submitList(it) }
        }*/

        /*
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
        }*/
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch{
            val tasks = db.taskDao().getAllTasks()
            Log.d("MainActivity", "dbresponse: $tasks")
            withContext(Dispatchers.Main){
                tasksAdapter.setData(tasks)
            }
        }
    }

    fun setupListener() {
        fab.setOnClickListener{
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun intentView(Id: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, detailTugas::class.java)
                .putExtra("intent_id", Id)
                .putExtra("intent_type", intentType)
        )
    }

    fun intentEdit(Id: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, NewTaskActivity::class.java)
                .putExtra("intent_id",Id)
                .putExtra("intent_type", intentType)
        )
    }



    private fun setupRecyclerView(){
        tasksAdapter = toDoItemAdapter(arrayListOf(), object : toDoItemAdapter.OnAdapterListener{
            override fun onClick(task: Task) {
                //Toast.makeText(applicationContext, task.taskTitle, Toast.LENGTH_SHORT).show()
                intentView(task.id, Constant.TYPE_READ)
            }
        })
        todo_recycler.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = tasksAdapter
        }
        todo_recycler?.setHasFixedSize(true)
    }


}