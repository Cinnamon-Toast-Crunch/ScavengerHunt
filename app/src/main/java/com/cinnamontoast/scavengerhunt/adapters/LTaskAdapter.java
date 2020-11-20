package com.cinnamontoast.scavengerhunt.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Quest;
import com.cinnamontoast.scavengerhunt.R;
import com.cinnamontoast.scavengerhunt.entities.LTask;

import java.util.ArrayList;

public class LTaskAdapter extends RecyclerView.Adapter<LTaskAdapter.LTaskViewHolder> {

    ArrayList<LTask> lTasksForRecycler;
    LTaskListFormatter lTaskListFormatter;

    public LTaskAdapter(ArrayList<LTask> lTasksForRecycler, LTaskListFormatter lTaskListFormatter) {
        this.lTasksForRecycler = lTasksForRecycler;
        this.lTaskListFormatter = lTaskListFormatter;
    }


    @NonNull
    @Override
    public LTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_l_task, parent, false);
        LTaskViewHolder lTaskViewHolder = new LTaskViewHolder(view);
        view.setOnClickListener(v -> {
            lTaskListFormatter.lTaskHighlighter(view, lTaskViewHolder.lTask);
            lTaskListFormatter.lTaskFormatter(lTaskViewHolder.lTask);
        });
        return lTaskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LTaskViewHolder holder, int position) {
        holder.lTask = lTasksForRecycler.get(position);
        ((TextView) holder.itemView.findViewById(R.id.lTaskRecyclerName)).setText(holder.lTask.objective);
        ((TextView) holder.itemView.findViewById(R.id.lTaskRecyclerPoints)).setText(holder.lTask.pointValue);

    }

    @Override
    public int getItemCount() {
        return lTasksForRecycler.size();
    }

    public static class LTaskViewHolder extends RecyclerView.ViewHolder{
        public LTask lTask;

        public LTaskViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public static interface LTaskListFormatter{
        public void lTaskFormatter(LTask lTask);
        public void lTaskHighlighter(View lTaskView, LTask lTask);
    }
}
