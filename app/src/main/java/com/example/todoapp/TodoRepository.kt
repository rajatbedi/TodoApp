package com.example.todoapp

class TodoRepository(
    private var todoDatabase: TodoDatabase
) {

    suspend fun upsert(todoItem: Todo) = todoDatabase.getTodoDao().upsert(todoItem)

    suspend fun deleteAll(isChecked: Boolean) = todoDatabase.getTodoDao().deleteAll(isChecked)

    fun getAllTodoItems() = todoDatabase.getTodoDao().getAllTodoItems()

}