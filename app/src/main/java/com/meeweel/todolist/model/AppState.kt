package com.meeweel.todolist.model

sealed class AppState {
    data class Success(val questData: MutableList<Quest>) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}