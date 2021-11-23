package com.example.papb_team5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_detail_tugas.*

class NewTaskActivity : AppCompatActivity() {
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
    }

}