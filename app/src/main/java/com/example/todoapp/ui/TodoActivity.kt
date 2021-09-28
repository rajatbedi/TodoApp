package com.example.todoapp.ui

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.ActivityTodoBinding
import android.view.inputmethod.InputMethodManager
import com.example.todoapp.others.TodoAdapter
import com.example.todoapp.data.db.TodoDatabase
import com.example.todoapp.data.repositories.TodoRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContainer
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class TodoActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: TodoViewModelFactory by instance()

    private lateinit var binding: ActivityTodoBinding

    lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this, factory).get(TodoViewModel::class.java)

        val adapter = TodoAdapter(mutableListOf(), viewModel)


        binding.apply {
            rvTodoItems.adapter = adapter
            rvTodoItems.layoutManager = LinearLayoutManager(this@TodoActivity)
        }

        viewModel.getAllTodoItems().observe(this, Observer {
            adapter.todoItems = it
            adapter.notifyDataSetChanged()

        })

        binding.apply {

            btnAdd.setOnClickListener {
                val etTitle = etTodoTitle.text.toString()
                etTodoTitle.text.clear()
                adapter.addTodo(etTitle,this@TodoActivity)

                closeSoftKeyboard(this@TodoActivity, etTodoTitle)
            }
        }
    }

    private fun closeSoftKeyboard(context: Context, v: View) {
        val iMm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        iMm.hideSoftInputFromWindow(v.windowToken, 0)
        v.clearFocus()
    }

}
