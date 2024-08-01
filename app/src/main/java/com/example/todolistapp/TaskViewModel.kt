package com.example.todolistapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao = AppDatabase.getDatabase(application).taskDao()

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    fun insert(task: Task) = viewModelScope.launch {
        taskDao.insertTask(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        taskDao.updateTask(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        taskDao.deleteTask(task)
    }
}

