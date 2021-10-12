package com.example.papb_team5

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers.Main

class fragment_home : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view4.setOnClickListener {
            activity?.let {
                val intent = Intent(it, detailTugas::class.java)
                it.startActivity(intent)
            }
        }
    }
}

