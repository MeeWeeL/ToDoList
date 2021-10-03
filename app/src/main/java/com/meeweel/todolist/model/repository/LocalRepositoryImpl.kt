package com.meeweel.todolist.model.repository

import com.meeweel.todolist.model.data.Quest
import com.meeweel.todolist.model.room.HistoryDao
import com.meeweel.todolist.model.room.convertHistoryEntityToQuest
import com.meeweel.todolist.model.room.convertQuestToEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) : LocalRepository {
    override fun getAllHistory(): MutableList<Quest> {
        return convertHistoryEntityToQuest(localDataSource.all())
    }

    override fun saveEntity(quest: Quest) {
        return localDataSource.insert(convertQuestToEntity(quest, 1))
    }

    override fun deleteAll() {
        localDataSource.deleteAll()
    }
    override fun getMyLocalQuestList() : MutableList<Quest> {
        val localMyQuestList: MutableList<Quest> = mutableListOf()
        for (item in convertHistoryEntityToQuest(localDataSource.all().reversed())) {
            if (item.list == 1) localMyQuestList.add(item)
        }
        return localMyQuestList
    }
    override fun getDeletedLocalQuestList() : MutableList<Quest> {
        val localMyQuestList: MutableList<Quest> = mutableListOf()
        for (item in convertHistoryEntityToQuest(localDataSource.all().reversed())) {
            if (item.list == 3) localMyQuestList.add(item)
        }
        return localMyQuestList
    }
    override fun getDoneLocalQuestList() : MutableList<Quest> {
        val localMyQuestList: MutableList<Quest> = mutableListOf()
        for (item in convertHistoryEntityToQuest(localDataSource.all().reversed())) {
            if (item.list == 2) localMyQuestList.add(item)
        }
        return localMyQuestList
    }
    override fun toTrash(quest: Quest) {
        localDataSource.delete(quest.title)
        localDataSource.insert(convertQuestToEntity(quest, 3))
    }
    override fun toDone(quest: Quest) {
        localDataSource.delete(quest.title)
        localDataSource.insert(convertQuestToEntity(quest, 2))
    }
    override fun toMain(quest: Quest) {
        localDataSource.delete(quest.title)
        localDataSource.insert(convertQuestToEntity(quest, 1))
    }
    override fun delete(quest: Quest) {
        localDataSource.delete(quest.title)
    }
}