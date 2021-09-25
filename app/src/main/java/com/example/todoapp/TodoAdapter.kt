package com.example.todoapp

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTodoBinding
import kotlin.math.log

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

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curItem = todoItems[position]
        holder.binding.apply {
            tvTodoTitle.text = curItem.title
            cbTodo.isChecked = curItem.isChecked
            toggleStrikeThru(tvTodoTitle, curItem.isChecked)
            cbTodo.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThru(tvTodoTitle, isChecked)
                curItem.isChecked = !curItem.isChecked
                Log.v("Rajat", "running update")
                viewModel.upsert(curItem)
                Log.v("Rajat", "cur item ${curItem}")
                Log.v("Rajat", "${todoItems}")
                Log.v("Rajat", "end update")
            }
        }
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }
}