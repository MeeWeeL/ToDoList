package com.meeweel.todolist.model.room

import androidx.room.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM HistoryEntity")
    fun all(): MutableList<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE title LIKE :title")
    fun getDataByWord(title: String): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Query("DELETE FROM HistoryEntity")
    fun deleteAll()

    @Query("DELETE FROM HistoryEntity WHERE title LIKE :title")
    fun delete(title: String)
}