package com.example.papb_team5.application

import android.app.Application
import com.example.papb_team5.room_database.TaskRepository
import com.example.papb_team5.room_database.TaskRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TasksApplication : Application(){
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {TaskRoomDatabase.getDatabase(this, applicationScope)}
    val repository by lazy {TaskRepository(database.taskDao())}

}