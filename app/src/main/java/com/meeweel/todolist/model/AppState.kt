package com.meeweel.todolist.model

import com.meeweel.todolist.model.data.Quest

sealed class AppState {
    data class Success(val questData: MutableList<Quest>) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}