package com.example.oompaloompashr.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.oompaloompashr.model.Result

@Database(
    entities = [Result::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class HRDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {

        @Volatile
        private var INSTANCE: HRDatabase? = null

        fun getInstance(context: Context): HRDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HRDatabase::class.java, "HRDatabase.db"
            )
                .build()
    }
}
