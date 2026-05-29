package com.example.app_todo.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application){

    private val dao = AppDatabase.getInstance(application).todoDao()

    val todos = dao.observeAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun addTodo(title: String){
        val trimmedTitle = title.trim()
        if(trimmedTitle.isEmpty()) return
        viewModelScope.launch {
            dao.insert(TodoEntity(title = trimmedTitle))
        }
    }

    fun toggleDone(todo: TodoEntity){
        viewModelScope.launch {
            dao.update(todo.copy(done = !todo.done))
        }
    }

    fun deleteTodo(todo: TodoEntity){
        viewModelScope.launch {
            dao.delete(todo)
        }
    }
}

