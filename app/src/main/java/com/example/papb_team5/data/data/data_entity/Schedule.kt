package com.example.papb_team5.data.data.data_entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule_table")
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val scheduleTitle: String,
    val scheduleTime: String

)