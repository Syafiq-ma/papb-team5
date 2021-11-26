package com.example.papb_team5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.papb_team5.data_entity.Task
import com.example.papb_team5.room_database.Constant
import com.example.papb_team5.room_database.TaskRoomDatabase
import kotlinx.android.synthetic.main.activity_detail_tugas.*
import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            txt_infoDetail.setText( tasks.taskDescription )
        }
    }

}
