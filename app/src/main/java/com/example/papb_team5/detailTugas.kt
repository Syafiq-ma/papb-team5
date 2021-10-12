package com.example.papb_team5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_tugas.*

class detailTugas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tugas)

        btn_back.setOnClickListener{

//            var intent = Intent(this@detailTugas, fragment_home::class.java)
//            startActivity(intent)
            finish()
        }
    }
}