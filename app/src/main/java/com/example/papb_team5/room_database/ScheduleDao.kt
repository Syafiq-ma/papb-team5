package com.example.papb_team5.room_database

import androidx.room.*
import com.example.papb_team5.data_entity.Schedule

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule_table")
    suspend fun getAllSchedules(): List<Schedule>

    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    @Query("SELECT * FROM schedule_table WHERE id =:schedule_id")
    suspend fun getSchedule(schedule_id: Int): List<Schedule>

    @Update
    suspend fun updateSchedule(schedule: Schedule)

    @Delete
    suspend fun deleteSchedule(schedule: Schedule)

    @Insert
    suspend fun insert(schedule: Schedule)

}