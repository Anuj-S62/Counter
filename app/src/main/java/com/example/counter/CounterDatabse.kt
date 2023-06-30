package com.example.counter

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Counter::class],
    version = 1
)
abstract class CounterDatabse:RoomDatabase() {
    abstract val dao:CounterDao
}