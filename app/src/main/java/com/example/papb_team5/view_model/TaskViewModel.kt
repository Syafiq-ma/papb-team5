package com.example.papb_team5.view_model
/*
import androidx.lifecycle.*
import com.example.papb_team5.data_entity.Task
import com.example.papb_team5.room_database.TaskRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

// TaskViewModel gets the repository that is the only dependency the ViewModel needs.
class TaskViewModel(private val repository: TaskRepository): ViewModel() {

    // used to cache the lists of tasks
    val allTasks: LiveData<List<Task>> = repository.allTasks.asLiveData()

    //launching a new coroutine
    fun insert(task: Task) = viewModelScope.launch{
        repository.insert(task)
    }
}

class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(TaskViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/