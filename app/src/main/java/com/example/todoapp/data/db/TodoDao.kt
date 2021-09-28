package com.example.todoapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.data.db.entities.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(todoItem: Todo)

    @Delete
    suspend fun delete(todoItem: Todo)

    @Query("DELETE FROM todo_items WHERE todo_checked = :isChecked")
    suspend fun deleteAll(isChecked: Boolean)

    @Query("SELECT * FROM todo_items")
    fun getAllTodoItems(): LiveData<MutableList<Todo>>
}