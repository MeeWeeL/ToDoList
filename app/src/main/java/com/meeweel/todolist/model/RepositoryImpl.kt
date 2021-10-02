package com.meeweel.todolist.model

class RepositoryImpl : Repository {

    override fun getQuestList(): List<Quest> = getMyLocalQuestList()
    override fun getDeletedQuestList(): List<Quest> = getDeletedLocalQuestList()
    override fun getDoneQuestList(): List<Quest> = getDoneLocalQuestList()
}