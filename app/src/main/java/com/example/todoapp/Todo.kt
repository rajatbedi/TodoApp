package com.example.todoapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class Todo (
    @ColumnInfo(name = "todo_title")
    val title: String,
    @ColumnInfo(name = "todo_checked")
    var isChecked : Boolean = false
        ){
    @PrimaryKey(autoGenerate = true)
    var id: Int?= null
}