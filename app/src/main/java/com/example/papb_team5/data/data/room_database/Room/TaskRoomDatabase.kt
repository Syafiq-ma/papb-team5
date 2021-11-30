package com.example.papb_team5.data.data.room_database.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.papb_team5.data.data.data_entity.Task
import com.example.papb_team5.data.data.room_database.Dao.TaskDao

@Database(
    entities = [Task::class],
    version = 2
)

abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile private var INSTANCE: TaskRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: buildDatabase(context).also{
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TaskRoomDatabase::class.java,
            "task_table.db"
        ).fallbackToDestructiveMigration().build()

    }

}