package com.example.papb_team5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detail_tugas.*

class detailTugas : AppCompatActivity() {

    companion object{
        const val LETTER = "letter"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tugas)

        val textView: TextView = findViewById<TextView>(R.id.editText_judulTugas)
        val letterId = intent?.extras?.getString(LETTER).toString()

        textView.text = letterId

        btn_back.setOnClickListener{
            var intent = Intent(this@detailTugas, fragment_home::class.java)
            startActivity(intent)
        }
    }
}