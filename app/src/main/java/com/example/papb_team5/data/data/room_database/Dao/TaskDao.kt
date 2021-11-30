package com.example.papb_team5.data.data.room_database.Dao

import androidx.room.*
import com.example.papb_team5.data.data.data_entity.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table")
    suspend fun getAllTasks(): List<Task>

    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    @Query("SELECT * FROM task_table WHERE id =:task_id")
    suspend fun getTask(task_id: Int): List<Task>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Insert
    suspend fun insert(task: Task)

}