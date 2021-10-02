package com.meeweel.todolist.model

interface Repository {

    fun getQuestList(): List<Quest>
    fun getDeletedQuestList(): List<Quest>
    fun getDoneQuestList(): List<Quest>
}