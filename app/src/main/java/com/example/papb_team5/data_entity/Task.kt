package com.example.papb_team5.data_entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey
    val taskTitle: String,
    val taskDescription: String
    )