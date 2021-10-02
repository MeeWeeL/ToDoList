package com.meeweel.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.todolist.model.AppState
import com.meeweel.todolist.model.Repository
import com.meeweel.todolist.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val repository: Repository = RepositoryImpl()) :
    ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    fun getData(): LiveData<AppState> {
        return liveDataToObserve
    }
    fun getQuestFromLocalSource() = getDataFromLocalSource()
    fun getDoneQuestFromLocalSource() = getDoneDataFromLocalSource()
    fun getDeletedQuestFromLocalSource() = getDeletedDataFromLocalSource()
    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(500)
            liveDataToObserve.postValue(
                AppState.Success(
                    repository.getQuestList()
                )
            )
        }.start()
    }
    private fun getDoneDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(500)
            liveDataToObserve.postValue(
                AppState.Success(
                    repository.getDoneQuestList()
                )
            )
        }.start()
    }
    private fun getDeletedDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(500)
            liveDataToObserve.postValue(
                AppState.Success(
                    repository.getDeletedQuestList()
                )
            )
        }.start()
    }
    fun saving() {

    }
}