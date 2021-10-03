package com.meeweel.todolist.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val list: Int,
    var title: String,
    var description: String,
    var image: Int,
    var imageInt: Int
)