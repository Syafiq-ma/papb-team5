package com.example.papb_team5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.papb_team5.application.TasksApplication
import com.example.papb_team5.view_model.TaskViewModel
import com.example.papb_team5.view_model.TaskViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = fragment_home()
        val profileFragment = fragment_profile()
        setCurrentFragment(mainFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(mainFragment)
                //.id.calendar->setCurrentFragment()
                R.id.profile->setCurrentFragment(profileFragment)
            }
            true
        }
    }
    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}