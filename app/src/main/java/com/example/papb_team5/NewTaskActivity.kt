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
        setupListener()
        Id = intent.getIntExtra("intent_id", 0)
        Toast.makeText(this, Id.toString(), Toast.LENGTH_SHORT).show()

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
    }

    /*
    private lateinit var editTitleView: EditText
    private lateinit var editDescView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        editTitleView = findViewById(R.id.edit_title)
        editDescView = findViewById(R.id.edit_desc)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener{
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTitleView.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else{
                val title = editTitleView.text.toString()
                val desc = editDescView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY1, title)
                replyIntent.putExtra(EXTRA_REPLY2, desc)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY1 = "com.example.android.tasklistsql.REPLY1"
        const val EXTRA_REPLY2 = "com.example.android.tasklistsql.REPLY2"
    }*/

}