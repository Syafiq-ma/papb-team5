package com.example.papb_team5

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.papb_team5.adapter.taskItemAdapter
import com.example.papb_team5.adapter.toDoItemAdapter
import com.example.papb_team5.application.TasksApplication
import com.example.papb_team5.data.datasource
import com.example.papb_team5.view_model.TaskViewModel
import com.example.papb_team5.view_model.TaskViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class fragment_home : Fragment(R.layout.fragment_home) {

    private val newTaskActivityRequestCode = 1
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as TasksApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        //val myDataset = datasource().loadAffirmations()

        val recyclerView = getView()?.findViewById<RecyclerView>(R.id.todo_recycler)
        recyclerView?.adapter = toDoItemAdapter(this, myDataset)

        recyclerView?.setHasFixedSize(true)*/
        val recyclerView = getView()?.findViewById<RecyclerView>(R.id.todo_recycler)
        recyclerView?.adapter = taskItemAdapter()

        val fab = getView()?.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(this@fragment_home, NewTaskActivity::class.java)
            startActivityForResult(intent, newTaskActivityRequestCode)
        }
    }
}

