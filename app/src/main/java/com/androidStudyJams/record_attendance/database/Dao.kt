package com.androidStudyJams.record_attendance.database

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    //insert
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    fun insertData(data:attendanceData)

    //update
    @Update
    fun update(data:attendanceData)

    //delete
    @Delete
    fun delete(data:attendanceData)

    //Query
    @Query("SELECT * FROM attendanceData")
    fun getAllData():List<attendanceData>

    @Query("delete from attendanceData where id = :idno")
    fun deleteById(idno:Int)
}