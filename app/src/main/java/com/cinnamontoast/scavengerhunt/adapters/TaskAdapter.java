package com.cinnamontoast.scavengerhunt.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Task;
import com.cinnamontoast.scavengerhunt.R;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    ArrayList<Task> taskForRecycler;
    TaskListFormatter taskListFormatter;

    public TaskAdapter(ArrayList<Task> taskForRecycler, TaskListFormatter taskListFormatter) {
        this.taskForRecycler = taskForRecycler;
        this.taskListFormatter = taskListFormatter;
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        view.setOnClickListener(v -> {
            taskListFormatter.taskFormatter(taskViewHolder.task);
            taskListFormatter.taskHighlighter(view, taskViewHolder.task);

        });
        return taskViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.task = taskForRecycler.get(position);
        ((TextView) holder.itemView.findViewById(R.id.taskName)).setText(holder.task.getObjective());
        ((TextView) holder.itemView.findViewById(R.id.taskPoints)).setText(holder.task.getPointValue().toString());

    }

    @Override
    public int getItemCount() {
        return taskForRecycler.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        public  Task task;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public static interface TaskListFormatter{
        public void taskFormatter(Task task);
        public void taskHighlighter(View taskView, Task task);
    }
}
