package com.sudiptacseseu.todo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class ToDo {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "todo_col")
    private String toDo;

    public ToDo(@NonNull String toDo) {
        this.toDo = toDo;
    }

    public String getToDo() {
        return toDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setToDo(@NonNull String toDo) {
        this.toDo = toDo;
    }
}
