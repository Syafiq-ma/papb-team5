package com.example.papb_team5

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.papb_team5.adapter.scheduleItemAdapter
import com.example.papb_team5.adapter.toDoItemAdapter
import com.example.papb_team5.data.data.data_entity.Schedule
import com.example.papb_team5.data.data.data_entity.Task
import com.example.papb_team5.data.data.room_database.Constant
import com.example.papb_team5.data.data.room_database.Room.ScheduleRoomDatabase
import com.example.papb_team5.data.data.room_database.Room.TaskRoomDatabase
import com.example.papb_team5.ui.activity.NewScheduleActivity
import com.example.papb_team5.ui.activity.NewTaskActivity
import com.example.papb_team5.ui.activity.detailTugas
import com.example.papb_team5.ui.activity.profileActivity
import kotlinx.android.synthetic.main.activity_detail_tugas.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView
import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.schedule_view.*
import kotlinx.android.synthetic.main.todo_view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { TaskRoomDatabase(this) }
    val db2 by lazy { ScheduleRoomDatabase (this) }
    lateinit var tasksAdapter: toDoItemAdapter
    lateinit var schedulesAdapter: scheduleItemAdapter

    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIFICATION_ID = 0

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
        setupRecyclerView2()

        createNotificationChannel()
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Notification Testing")
            .setContentText("It is working!")
            .setSmallIcon(R.drawable.ic_btn_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)

        imageView2.setOnClickListener{
            notificationManager.notify(NOTIFICATION_ID, notification)
        }

    }

    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply{
                    lightColor = Color.GREEN
                    enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun onStart() {
        super.onStart()
        loadTask()
        loadSchedule()
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

    fun loadSchedule() {
        CoroutineScope(Dispatchers.IO).launch{
            val schedules = db2.scheduleDao().getAllSchedules()
            Log.d("MainActivity", "dbresponse: $schedules")
            withContext(Dispatchers.Main){
                schedulesAdapter.setData(schedules)
            }
        }
    }

    fun setupListener() {
        fab.setOnClickListener{
            intentEdit(0, Constant.TYPE_CREATE)
        }
        txt_addSchedule.setOnClickListener{
            intentScheduleEdit(0, Constant.TYPE_CREATE)
        }
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                }
                R.id.calendar ->{
                    Toast.makeText(applicationContext, "Sorry, feature is in work!", Toast.LENGTH_SHORT).show()
                }

                R.id.profile ->{
                    startActivity(Intent(applicationContext, profileActivity::class.java))
                }

            }
            true
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

    fun intentScheduleView(Id: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, NewScheduleActivity::class.java)
                .putExtra("intent_id", Id)
                .putExtra("intent_type", intentType)
        )
    }

    fun intentScheduleEdit(Id: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, NewScheduleActivity::class.java)
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
    }

    private fun setupRecyclerView2(){
        schedulesAdapter = scheduleItemAdapter(this, arrayListOf(), object: scheduleItemAdapter.OnAdapterListener{
            override fun onClick(schedule: Schedule){
                intentScheduleView(schedule.id, Constant.TYPE_READ)
            }
            override fun onUpdate(schedule: Schedule){
                intentScheduleEdit(schedule.id, Constant.TYPE_UPDATE)
            }
            override fun onDelete(schedule: Schedule){
                deleteScheduleDialog(schedule)
                createChannel(
                    getString(R.string.scheduleNotificationId),
                    getString(R.string.scheduleNotificationname)
                )
            }
        })
        schedule_recycler.apply{
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = schedulesAdapter
        }
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

    private fun deleteScheduleDialog(schedule: Schedule){
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply{
            setTitle("Konfirmasi")
            setMessage("Yakin hapus ${schedule.scheduleTitle}?")
            setNegativeButton("Batal") { dialog, i ->
                dialog.dismiss()
            }
            setPositiveButton("Hapus") { dialog, i ->
                dialog.dismiss()
                CoroutineScope(Dispatchers.IO).launch{
                    db2.scheduleDao().deleteSchedule(schedule)
                    loadSchedule()
                }
            }
        }

        alertDialog.show()
    }

    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.scheduleNotificationChannelDesc)

            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)

        }
    }

}