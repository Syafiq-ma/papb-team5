package com.example.papb_team5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import com.example.papb_team5.adapter.toDoItemAdapter
import com.example.papb_team5.data_entity.Task
import com.example.papb_team5.room_database.Constant
import com.example.papb_team5.room_database.TaskRoomDatabase
import kotlinx.android.synthetic.main.activity_detail_tugas.*
import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class detailTugas : AppCompatActivity() {

    /*
    companion object{
        const val TITLE = "letter"
        const val DESC = "letter"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tugas)

        val titleTextView: TextView = findViewById<TextView>(R.id.editText_judulTugas)
        val descTextView: TextView = findViewById<TextView>(R.id.txt_infoDetail)
        val title = intent?.extras?.getString(TITLE).toString()
        val desc = intent?.extras?.getString(DESC).toString()

        titleTextView.text = title
        descTextView.text = desc

        btn_back.setOnClickListener{
            var intent = Intent(this@detailTugas, MainActivity::class.java)
            startActivity(intent)
        }
    }*/

    val db by lazy { TaskRoomDatabase(this) }
    private var Id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tugas)
        setupView()
        //setupListener()

        btn_elipsis_layout.setOnClickListener{
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId){
                    R.id.nav_edit ->{
                        Toast.makeText(applicationContext, "Edit Button pressed", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.nav_delete -> {
                        Toast.makeText(applicationContext, "Delete Button pressed", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.todo_popup_menu)
            popupMenu.show()
        }

        val btnBack: Button = findViewById(R.id.btn_back)
        btnBack.setOnClickListener{
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }

    fun setupView(){
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType){
            Constant.TYPE_CREATE -> {

            }
            Constant.TYPE_READ -> {
                getTask()
            }
            Constant.TYPE_UPDATE -> {
            }
        }
    }

    /*
    fun setupListener(){
        button_save.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                db.taskDao().insert(
                    Task(0, edit_title.text.toString(),
                        edit_desc.text.toString())
                )

                finish()
            }
        }
    }*/

    fun getTask(){
        Id = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch{
            val tasks = db.taskDao().getTask(Id)[0]
            editText_judulTugas.setText( tasks.taskTitle )
            txt_infoDetail.setText( tasks.taskDescription)
            edit_date.setText( tasks.taskDate)
        }
    }

}
