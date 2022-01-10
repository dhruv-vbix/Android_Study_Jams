package com.androidStudyJams.record_attendance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [attendanceData::class],version = 3,exportSchema = false)
abstract class Database() :RoomDatabase() {

    abstract fun getDao():Dao

    companion object
    {
        @Volatile
        private var instance:com.androidStudyJams.record_attendance.database.Database?=null
        private val LOCK = Any()

        operator fun invoke(context:Context) = instance?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance = it }
        }

        fun buildDatabase(context:Context) =
            databaseBuilder(context.applicationContext,
                com.androidStudyJams.record_attendance.database.Database::class.java,
                "attendance.db")
                .allowMainThreadQueries()
                .build()


    }

}