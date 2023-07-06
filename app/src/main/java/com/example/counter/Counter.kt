package com.example.counter

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Counter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val counterName:String,
    val count:String,
    val inc:String,
    val dec:String,
//    val col: Color,
    val red:Int,
    val blue:Int,
    val green:Int,
)
