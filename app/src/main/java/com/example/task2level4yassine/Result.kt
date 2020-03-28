package com.example.task2level4yassine

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Yassine el Aatiaoui
 * Student Nr 500767860
 */

/**
 *  Class Result
 */

@Entity(tableName = "resultTable")
data class Result(

    @ColumnInfo(name = "playerChoice")
    val playerChoice: String,
    @ColumnInfo(name = "computerChoice")
    val computerChoice: String,
    @ColumnInfo(name = "winnar")
    val winnar: String,
    @ColumnInfo(name = "dateTime")
    val dateTime: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

)