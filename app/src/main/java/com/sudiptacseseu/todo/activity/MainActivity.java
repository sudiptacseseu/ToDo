package com.sudiptacseseu.todo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sudiptacseseu.todo.R;
import com.sudiptacseseu.todo.model.ToDo;
import com.sudiptacseseu.todo.viewmodel.ToDoViewModel;
import com.sudiptacseseu.todo.adapter.ToDoListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_TODO_REQUEST_CODE = 1;
    private ToDoListAdapter toDoListAdapter;
    private ToDoViewModel toDoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        toDoListAdapter = new ToDoListAdapter(this);
        recyclerView.setAdapter(toDoListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, NewToDoActivity.class);
               startActivityForResult(intent, NEW_TODO_REQUEST_CODE);
            }
        });

        toDoViewModel.getAllToDos().observe(this, new Observer<List<ToDo>>() {
            @Override
            public void onChanged(@Nullable List<ToDo> toDos) {
                //update the cached copy of todos in the adapter
                toDoListAdapter.setToDos(toDos);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_TODO_REQUEST_CODE && resultCode == RESULT_OK ) {
            assert data != null;
            ToDo toDo = new ToDo(data.getStringExtra(NewToDoActivity.EXTRA_REPLY));
            toDoViewModel.insert(toDo);
        }else {
            Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_LONG)
                    .show();
        }
    }
}
