package com.example.papb_team5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.papb_team5.adapter.toDoItemAdapter
import com.example.papb_team5.data.datasource

class fragment_home : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myDataset = datasource().loadAffirmations()

        val recyclerView = getView()?.findViewById<RecyclerView>(R.id.todo_recycler)
        recyclerView?.adapter = toDoItemAdapter(this, myDataset)

        recyclerView?.setHasFixedSize(true)
    }
}

