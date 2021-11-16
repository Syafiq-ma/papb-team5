package com.example.papb_team5.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.papb_team5.data_entity.Task

@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
public abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskRoomDatabase? = null

        fun getDatabase(context: Context): TaskRoomDatabase {
            // if INSTANCE is null, create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}