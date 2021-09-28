package com.example.todoapp.others

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.constants.ColorsList
import com.example.todoapp.ui.TodoViewModel
import com.example.todoapp.data.db.entities.Todo
import com.example.todoapp.databinding.ItemTodoBinding

class TodoAdapter(var todoItems: MutableList<Todo>, var viewModel: TodoViewModel): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    private fun toggleStrikeThru(tvTitle: TextView, isChecked: Boolean){
        if (isChecked) {
            tvTitle.paintFlags = tvTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvTitle.paintFlags = tvTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo(title: String, context: Context,){
        if (title.isNullOrEmpty()) {
            Toast.makeText(
                context,
                "Please Enter Todo Title", Toast.LENGTH_SHORT
            ).show()
            return
        }
        val todoItem = Todo(title = title, false, ColorsList.COLORS.random())
        viewModel.upsert(todoItem)
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curItem = todoItems[position]
        holder.binding.apply {
            tvTodoTitle.text = curItem.title
            cbTodo.isChecked = curItem.isChecked
            toggleStrikeThru(tvTodoTitle, curItem.isChecked)
            todoItemLayout.setBackgroundResource(curItem.color)


            cbTodo.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThru(tvTodoTitle, isChecked)
                curItem.isChecked = !curItem.isChecked
                viewModel.upsert(curItem)
            }

            ivDelete.setOnClickListener {
                viewModel.delete(curItem)
            }

        }
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }
}