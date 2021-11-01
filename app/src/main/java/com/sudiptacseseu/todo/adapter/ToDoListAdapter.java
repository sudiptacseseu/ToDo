package com.sudiptacseseu.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sudiptacseseu.todo.R;
import com.sudiptacseseu.todo.model.ToDo;

import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder> {

    private final LayoutInflater toDoInflater;
    private List<ToDo> toDoList; //cached copy of todo items

    public ToDoListAdapter(Context context) {
        toDoInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = toDoInflater.inflate(R.layout.recyclerview_item, viewGroup, false);

        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder toDoViewHolder, int position) {

        if (toDoList != null) {
            ToDo current = toDoList.get(position);
            toDoViewHolder.toDoTextView.setText(current.getToDo());
        } else {
            toDoViewHolder.toDoTextView.setText(R.string.no_todo);
        }
    }

    public void setToDos(List<ToDo> toDos) {
        toDoList = toDos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (toDoList != null)
            return toDoList.size();
        else return 0;
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder {
        public TextView toDoTextView;

        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            toDoTextView = itemView.findViewById(R.id.todoTextViewId);
        }
    }
}
