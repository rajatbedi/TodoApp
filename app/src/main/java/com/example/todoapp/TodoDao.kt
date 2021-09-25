package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(todoItem: Todo)

    @Query("DELETE FROM todo_items WHERE todo_checked = :isChecked")
    suspend fun deleteAll(isChecked: Boolean)

    @Query("SELECT * FROM todo_items")
    fun getAllTodoItems(): LiveData<MutableList<Todo>>
}