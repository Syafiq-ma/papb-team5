package com.example.papb_team5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class accountProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_profile)

        setSupportActionBar(findViewById(R.id.main_toolbar))
    }
}