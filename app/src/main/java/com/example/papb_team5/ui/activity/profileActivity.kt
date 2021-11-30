package com.example.papb_team5.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.papb_team5.MainActivity
import com.example.papb_team5.R
import com.example.papb_team5.data.data.room_database.Constant
import kotlinx.android.synthetic.main.activity_detail_tugas.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.bottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class profileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){

                R.id.home -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }

                R.id.calendar -> {
                    Toast.makeText(applicationContext, "Sorry, feature is in work!", Toast.LENGTH_SHORT).show()
                }
                //R.id.profile->setCurrentFragment(profileFragment)
                /*
                R.id.profile->{
                    val intent = Intent(this@MainActivity, profileActivity::class.java)
                    startActivity(intent)
                }
                 */
            }
            true
        }
    }


}
