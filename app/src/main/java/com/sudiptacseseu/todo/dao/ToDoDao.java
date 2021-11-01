package com.sudiptacseseu.todo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sudiptacseseu.todo.model.ToDo;

import java.util.List;

@Dao
public interface ToDoDao {
    //CRUD
    @Insert
    void insert(ToDo toDo);

    @Query("DELETE FROM todo_table")
    void deleteAll();

    @Query("DELETE FROM todo_table WHERE id = :id")
    int deleteAToDo(int id);


    @Query("UPDATE todo_table SET todo_col = :todoText WHERE id = :id")
    int updateToDoItem(int id, String todoText);

    @Query("SELECT * FROM todo_table ORDER BY todo_col DESC")
    LiveData<List<ToDo>> getAllToDos();

}
