package com.example.todoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.ActivityTodoBinding
import android.view.inputmethod.InputMethodManager
import android.widget.Toast


class TodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoBinding

    lateinit var viewModel: TodoViewModel

    private lateinit var factory: TodoViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val database = TodoDatabase(this)
        val repository = TodoRepository(database)
        factory = TodoViewModelFactory(repository)

        viewModel = ViewModelProviders.of(this, factory).get(TodoViewModel::class.java)

        val adapter = TodoAdapter(mutableListOf(), viewModel)


        binding.apply {
            rvTodoItems.adapter = adapter
            rvTodoItems.layoutManager = LinearLayoutManager(this@TodoActivity)
        }

        viewModel.getAllTodoItems().observe(this, Observer {
            Log.v("Rajat", "running observing")
            adapter.todoItems = it
            Log.v("Rajat", "${adapter.todoItems}")
            Log.v("Rajat", "end observing")
            adapter.notifyDataSetChanged()

        })

        binding.apply {
            btnDelete.setOnClickListener {
                    Log.v("Rajat", "running delete")
                    viewModel.deleteAll(true)
                    adapter.todoItems.removeAll { todo ->
                        todo.isChecked
                    }
                    Log.v("Rajat", "${adapter.todoItems}")

                    Log.v("Rajat", "end delete")

            }

            btnAdd.setOnClickListener {
                val etTitle = etTodoTitle.text.toString()
                etTodoTitle.text.clear()
                if(etTitle.isNullOrEmpty()){
                    Toast.makeText(this@TodoActivity,
                        "Please Enter Todo Title", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val todoItem = Todo(title = etTitle, false)
                Log.v("Rajat", "running insert")
                viewModel.upsert(todoItem)
                adapter.todoItems.add(todoItem)
                adapter.notifyItemInserted(adapter.todoItems.size - 1)
                Log.v("Rajat", "${adapter.todoItems}")
                Log.v("Rajat", "end insert")


            }
        }
    }
}
