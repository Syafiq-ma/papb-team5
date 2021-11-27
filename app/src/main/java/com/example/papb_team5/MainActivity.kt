package com.example.papb_team5

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.papb_team5.adapter.toDoItemAdapter
import com.example.papb_team5.data_entity.Task
import com.example.papb_team5.room_database.Constant
import com.example.papb_team5.room_database.TaskRoomDatabase
import kotlinx.android.synthetic.main.activity_detail_tugas.*
import kotlinx.android.synthetic.main.activity_detail_tugas.btn_elipsis_layout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.android.synthetic.main.todo_view.*
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

    }

    override fun onStart() {
        super.onStart()
        loadTask()
    }

    fun loadTask(){
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
                .putExtra("intent_id", Id)
                .putExtra("intent_type", intentType)
        )
    }



    private fun setupRecyclerView(){
        tasksAdapter = toDoItemAdapter(this, arrayListOf(), object : toDoItemAdapter.OnAdapterListener{

            override fun onClick(task: Task) {
                // read detail task
                intentView(task.id, Constant.TYPE_READ)
            }

            override fun onUpdate(task: Task) {
                intentEdit(task.id, Constant.TYPE_UPDATE)
            }
            override fun onDelete(task: Task) {
                deleteDialog(task)
            }
        })
        todo_recycler.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = tasksAdapter
        }
        //todo_recycler?.setHasFixedSize(true)
    }

    private fun deleteDialog(task: Task){
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply{
            setTitle("Konfirmasi")
            setMessage("Yakin hapus ${task.taskTitle}?")
            setNegativeButton("Batal") { dialog, i ->
                dialog.dismiss()
            }
            setPositiveButton("Hapus") { dialog, i ->
                dialog.dismiss()
                CoroutineScope(Dispatchers.IO).launch{
                    db.taskDao().deleteTask(task)
                    loadTask()
                }
            }
        }
        alertDialog.show()
    }

}