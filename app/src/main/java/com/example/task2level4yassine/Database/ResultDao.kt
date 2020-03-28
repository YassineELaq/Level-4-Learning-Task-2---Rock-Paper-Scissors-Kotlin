package com.example.task2level4yassine.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.task2level4yassine.Result

/**
 * @author Yassine el Aatiaoui
 * Student Nr 500767860
 */


/**
 * Dao class (Data access object)
 */

@Dao
interface ResultDao {

    @Query("SELECT * FROM resultTable")
    suspend fun getAllResults(): List<Result>

    @Insert
    suspend fun insertResult(product: Result)

    @Query("DELETE FROM resultTable")
    suspend fun deleteAllResults()

    @Query("SELECT COUNT(id) as total FROM resultTable WHERE winnar = :string")
    suspend fun getCountOf(string: String): Int
}