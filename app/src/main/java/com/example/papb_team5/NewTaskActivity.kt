package com.example.papb_team5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.papb_team5.data_entity.Task
import com.example.papb_team5.room_database.Constant
import com.example.papb_team5.room_database.TaskRoomDatabase
import kotlinx.android.synthetic.main.activity_detail_tugas.*
import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTaskActivity : AppCompatActivity() {

    val db by lazy {TaskRoomDatabase(this)}
    private var Id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        setupView()
        setupListener()
        Id = intent.getIntExtra("intent_id", 0)
        Toast.makeText(this, Id.toString(), Toast.LENGTH_SHORT).show()

        val btnBack: Button = findViewById(R.id.btn_back)
        btnBack.setOnClickListener{
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

    }

    fun setupView(){
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType){
            Constant.TYPE_CREATE -> {
                button_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {

            }
            Constant.TYPE_UPDATE -> {
                getTask()
                button_save.visibility = View.GONE
                button_update.visibility = View.VISIBLE
            }
        }
    }

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
        button_update.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                db.taskDao().updateTask(
                    Task(Id, edit_title.text.toString(),
                        edit_desc.text.toString())
                )

                finish()
            }
        }
    }

    fun getTask(){
        Id = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch{
            val tasks = db.taskDao().getTask(Id)[0]
            edit_title.setText( tasks.taskTitle )
            edit_desc.setText( tasks.taskDescription)
        }
    }

}