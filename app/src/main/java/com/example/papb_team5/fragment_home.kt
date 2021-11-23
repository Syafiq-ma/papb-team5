package com.example.papb_team5

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.papb_team5.adapter.TaskItemAdapter
import com.example.papb_team5.adapter.toDoItemAdapter
import com.example.papb_team5.application.TasksApplication
import com.example.papb_team5.data.datasource
import com.example.papb_team5.view_model.TaskViewModel
import com.example.papb_team5.view_model.TaskViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class fragment_home : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        //val myDataset = datasource().loadAffirmations()

        val recyclerView = getView()?.findViewById<RecyclerView>(R.id.todo_recycler)
        recyclerView?.adapter = toDoItemAdapter(this, myDataset)

        recyclerView?.setHasFixedSize(true)*/
        val recyclerView = getView()?.findViewById<RecyclerView>(R.id.todo_recycler)
        recyclerView?.adapter = TaskItemAdapter()
    }
}

