package com.example.todoapp.ui

import androidx.lifecycle.ViewModel
import com.example.todoapp.data.db.entities.Todo
import com.example.todoapp.data.repositories.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(
    private val todoRepository: TodoRepository
): ViewModel() {

    fun upsert(todoItem: Todo) = CoroutineScope(Dispatchers.Main).launch {
        todoRepository.upsert(todoItem)
    }

    fun delete(todoItem: Todo) = CoroutineScope(Dispatchers.Main).launch {
        todoRepository.delete(todoItem)
    }

    fun deleteAll(isChecked: Boolean) = CoroutineScope(Dispatchers.Main).launch {
        todoRepository.deleteAll(isChecked)
    }


    fun getAllTodoItems() = todoRepository.getAllTodoItems()

}