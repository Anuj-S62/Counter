package com.example.counter

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterDao {
    @Upsert
    suspend fun upsertCounter(counter:Counter)

    @Delete
    suspend fun deleteCounter(counter: Counter)

    @Query("SELECT * FROM counter order by id DESC")
    fun getAllCounter() : Flow<List<Counter>>

    @Query("UPDATE counter SET count = :count WHERE id = :id")
    suspend fun incrementCounter(count:String,id:Int)
}