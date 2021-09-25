package com.example.todoapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TodoViewModel(
    private val todoRepository: TodoRepository
): ViewModel() {

    fun upsert(todoItem: Todo) = CoroutineScope(Dispatchers.Main).launch {
        todoRepository.upsert(todoItem)
    }

    fun deleteAll(isChecked: Boolean) = CoroutineScope(Dispatchers.Main).launch {
        todoRepository.deleteAll(isChecked)
    }


    fun getAllTodoItems() = todoRepository.getAllTodoItems()

}