package com.example.papb_team5.ui.activity

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.papb_team5.MainActivity
import com.example.papb_team5.R
import com.example.papb_team5.data.data.data_entity.Task
import com.example.papb_team5.data.data.room_database.Constant
import com.example.papb_team5.data.data.room_database.Room.TaskRoomDatabase
import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NewTaskActivity : AppCompatActivity() {

    val db by lazy { TaskRoomDatabase(this) }
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
        val calendar = Calendar.getInstance()
        edit_date.setOnClickListener {
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    edit_date.setText(SimpleDateFormat("dd/MM/yyyy", Locale.US).format(calendar.time))
                }
            val datepicker = DatePickerDialog( this, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datepicker.show()
        }

        button_save.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                db.taskDao().insert(
                    Task(0, edit_title.text.toString(),
                        edit_desc.text.toString(),
                        edit_date.text.toString())
                )

                finish()
            }
        }
        button_update.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                db.taskDao().updateTask(
                    Task(Id, edit_title.text.toString(),
                        edit_desc.text.toString(),
                        edit_date.text.toString())
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
            edit_date.setText(tasks.taskDate)
        }
    }

}