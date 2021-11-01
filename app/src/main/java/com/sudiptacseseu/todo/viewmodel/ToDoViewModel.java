package com.sudiptacseseu.todo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sudiptacseseu.todo.model.ToDo;
import com.sudiptacseseu.todo.repository.ToDoRepository;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {
    private ToDoRepository toDoRepository;
    private LiveData<List<ToDo>> allToDos;

    public ToDoViewModel(@NonNull Application application) {
        super(application);
        toDoRepository = new ToDoRepository(application);
        allToDos = toDoRepository.getAllToDos();
    }

    public LiveData<List<ToDo>> getAllToDos() {
        return allToDos;
    }

    public void insert(ToDo toDo) {
        toDoRepository.insert(toDo);
    }
}
