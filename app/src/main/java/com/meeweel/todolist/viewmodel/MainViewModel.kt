package com.meeweel.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.todolist.model.*
import com.meeweel.todolist.room.App.Companion.getHistoryDao
import java.lang.Thread.sleep

class MainViewModel(private val repository: LocalRepository = LocalRepositoryImpl(getHistoryDao())) :
ViewModel() {
private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
fun getData(): LiveData<AppState> {
    return liveDataToObserve
}
fun getQuestFromLocalSource() {
    sync()
    return getDataFromLocalSource()
}
    fun getDoneQuestFromLocalSource() {
        sync()
        return getDoneDataFromLocalSource()
    }
    fun getDeletedQuestFromLocalSource() {
        sync()
        return getDeletedDataFromLocalSource()
    }
    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(100)
            liveDataToObserve.postValue(
                AppState.Success(
                    repository.getMyLocalQuestList()
                )
            )
        }.start()
    }
    private fun getDoneDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(100)
            liveDataToObserve.postValue(
                AppState.Success(
                    repository.getDoneLocalQuestList()
                )
            )
        }.start()
    }
    private fun getDeletedDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(100)
            liveDataToObserve.postValue(
                AppState.Success(
                    repository.getDeletedLocalQuestList()
                )
            )
        }.start()
    }
    fun saveChanges(quest: Quest, newQuest: Quest) {
        repository.delete(quest)
        repository.saveEntity(newQuest)
    }
    fun saveNewQuest(quest: Quest) {
        repository.saveEntity(quest)
    }
    fun toDelete(quest: Quest) {
        repository.toTrash(quest)
    }
    fun toMain(quest: Quest) {
        repository.toMain(quest)
    }
    fun toDone(quest: Quest) {
        repository.toDone(quest)
    }
    fun delete(quest: Quest) {
        repository.delete(quest)
    }
    fun sync() {
        for (item in changingQuest) {
            when(item.list) {
                0 -> delete(item)
                1 -> toMain(item)
                2 -> toDone(item)
                3 -> toDelete(item)
            }
        }
        changingQuest = mutableListOf()
    }
}