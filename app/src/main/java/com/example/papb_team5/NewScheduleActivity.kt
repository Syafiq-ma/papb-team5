package com.example.papb_team5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.papb_team5.data_entity.Schedule
import com.example.papb_team5.room_database.Constant
import com.example.papb_team5.room_database.ScheduleRoomDatabase
import kotlinx.android.synthetic.main.activity_new_schedule.*
import kotlinx.android.synthetic.main.activity_new_schedule.button_save
import kotlinx.android.synthetic.main.activity_new_schedule.button_update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewScheduleActivity : AppCompatActivity() {

    val db2 by lazy { ScheduleRoomDatabase(this) }
    private var Id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_schedule)
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
                button_save.visibility = View.VISIBLE
            }
            Constant.TYPE_READ -> {
                getTask()
                button_update.visibility = View.GONE
                button_save.visibility = View.GONE
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
                db2.scheduleDao().insert(
                    Schedule(0, txt_addSchedule.text.toString())
                )
                finish()
            }
        }
        button_update.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                db2.scheduleDao().updateSchedule(
                    Schedule(0, txt_addSchedule.text.toString())
                )

                finish()
            }
        }
    }

    fun getTask(){
        Id = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch{
            val schedules = db2.scheduleDao().getSchedule(Id)[0]
            txt_addSchedule.setText( schedules.scheduleTitle )
        }
    }
}