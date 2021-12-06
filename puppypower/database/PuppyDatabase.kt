package com.example.puppypower.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PuppyPowerEntity::class], version = 1)
abstract class PuppyDatabase: RoomDatabase() {
    abstract fun fileNameDao(): PuppyDao

    companion object {
        @Volatile
        private var INSTANCE: PuppyDatabase? = null

        fun getDatabase(context: Context): PuppyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    PuppyDatabase::class.java,
                    "app_database")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}





