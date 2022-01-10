package com.androidStudyJams.record_attendance.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendanceData")
data class attendanceData(

    var date:String,
    var subject:String,
    var std:String,
    var cls:String,
    var total:String,
    var absent:String,
    var note:String
        )
{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

