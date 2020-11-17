package com.cinnamontoast.scavengerhunt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Hunt;
import com.amplifyframework.datastore.generated.model.Location;
import com.amplifyframework.datastore.generated.model.Quest;

import java.util.ArrayList;

public class HuntAdapter extends RecyclerView.Adapter<HuntAdapter.HuntViewHolder> {

    ArrayList<Hunt> huntForRecycler;
    HuntListFormatter huntListFormatter;

    public HuntAdapter(ArrayList<Hunt> huntForRecycler, HuntListFormatter huntListFormatter) {
        this.huntForRecycler = huntForRecycler;
        this.huntListFormatter = huntListFormatter;
    }


    @NonNull
    @Override
    public HuntViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_hunt, parent, false);
        HuntViewHolder huntViewHolder = new HuntViewHolder(view);
        view.setOnClickListener(v -> {
            huntListFormatter.huntFormatter(huntViewHolder.hunt);

        });
        return huntViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HuntViewHolder holder, int position) {
        holder.hunt = huntForRecycler.get(position);
        ((TextView) holder.itemView.findViewById(R.id.huntName)).setText(holder.hunt.getName());
        ((TextView) holder.itemView.findViewById(R.id.huntPoints)).setText(holder.hunt.getTotalPoints().toString());

    }

    @Override
    public int getItemCount() {
        return huntForRecycler.size();
    }

    public static class HuntViewHolder extends RecyclerView.ViewHolder{
        public  Hunt hunt;

        public HuntViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public static interface HuntListFormatter{
        public void huntFormatter(Hunt hunt);
    }
}
