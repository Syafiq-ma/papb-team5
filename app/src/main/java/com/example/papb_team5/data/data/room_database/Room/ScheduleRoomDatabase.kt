package com.example.papb_team5.data.data.room_database.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.papb_team5.data.data.data_entity.Schedule
import com.example.papb_team5.data.data.room_database.Dao.ScheduleDao

@Database(
    entities = [Schedule::class],
    version = 2
)

abstract class ScheduleRoomDatabase : RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile private var INSTANCE: ScheduleRoomDatabase? = null
        private val LOCK = Any()


        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: buildDatabase(context).also{
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ScheduleRoomDatabase::class.java,
            "schedule_table.db"
        ).fallbackToDestructiveMigration().build()

    }

}