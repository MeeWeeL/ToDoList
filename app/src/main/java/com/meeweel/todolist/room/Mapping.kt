package com.meeweel.todolist.room

import com.meeweel.todolist.model.Quest

fun convertHistoryEntityToQuest(entityList: List<HistoryEntity>): MutableList<Quest> {
    val a = entityList.map {
        Quest(it.list, it.title, it.description, it.image, it.imageInt)
    }
    return a.toMutableList()
}

fun convertQuestToEntity(quest: Quest, list: Int): HistoryEntity {
    return HistoryEntity(0, list, quest.title,quest.description,image = quest.image,imageInt = quest.imageInt)
}