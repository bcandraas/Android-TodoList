package com.example.todolistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), TaskAdapter.OnItemClickListener {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        adapter = TaskAdapter(emptyList(), this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        taskViewModel.allTasks.observe(this, Observer { tasks ->
            tasks?.let { adapter.updateTasks(it) }
        })

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            // Handle add task
        }
    }

    override fun onEditClick(task: Task) {
        // Handle edit task
    }

    override fun onDeleteClick(task: Task) {
        taskViewModel.delete(task)
    }
}
