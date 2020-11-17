package com.cinnamontoast.scavengerhunt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Quest;

import java.util.ArrayList;

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.QuestViewHolder> {

    ArrayList<Quest> questsForRecycler;
    QuestListFormatter questListFormatter;

    public QuestAdapter(ArrayList<Quest> questsForRecycler, QuestListFormatter questListFormatter) {
        this.questsForRecycler = questsForRecycler;
        this.questListFormatter = questListFormatter;
    }


    @NonNull
    @Override
    public QuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_quest, parent, false);
        QuestViewHolder questViewHolder = new QuestViewHolder(view);
        view.setOnClickListener(v -> {
            questListFormatter.questFormatter(questViewHolder.quest);

        });
        return questViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestViewHolder holder, int position) {
        holder.quest = questsForRecycler.get(position);
        ((TextView) holder.itemView.findViewById(R.id.questTitle)).setText(holder.quest.getTitle());

    }

    @Override
    public int getItemCount() {
        return questsForRecycler.size();
    }

    public static class QuestViewHolder extends RecyclerView.ViewHolder{
        public Quest quest;

        public QuestViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public static interface QuestListFormatter{
        public void questFormatter(Quest quest);
    }
}
