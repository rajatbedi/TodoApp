package com.example.todoapp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class Todo (
    @ColumnInfo(name = "todo_title")
    val title: String,
    @ColumnInfo(name = "todo_checked")
    var isChecked : Boolean = false,
    @ColumnInfo(name = "todo_color")
    var color : Int
        ){
    @PrimaryKey(autoGenerate = true)
    var id: Int?= null
}