package com.example.todoapp.data.repositories

import com.example.todoapp.data.db.TodoDatabase
import com.example.todoapp.data.db.entities.Todo

class TodoRepository(
    private var todoDatabase: TodoDatabase
) {

    suspend fun upsert(todoItem: Todo) = todoDatabase.getTodoDao().upsert(todoItem)

    suspend fun delete(todoItem: Todo) = todoDatabase.getTodoDao().delete(todoItem)

    suspend fun deleteAll(isChecked: Boolean) = todoDatabase.getTodoDao().deleteAll(isChecked)

    fun getAllTodoItems() = todoDatabase.getTodoDao().getAllTodoItems()

}