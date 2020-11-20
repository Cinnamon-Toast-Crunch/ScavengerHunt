package com.cinnamontoast.scavengerhunt.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cinnamontoast.scavengerhunt.R;
import com.cinnamontoast.scavengerhunt.entities.LLocation;

import java.util.ArrayList;

public class LLocationAdapter extends RecyclerView.Adapter<LLocationAdapter.LLocationViewHolder> {

    ArrayList<LLocation> lLocationsForRecycler;
    LLocationListFormatter lLocationListFormatter;

    public LLocationAdapter(ArrayList<LLocation> lLocationsForRecycler, LLocationListFormatter lLocationListFormatter) {
        this.lLocationsForRecycler = lLocationsForRecycler;
        this.lLocationListFormatter = lLocationListFormatter;
    }


    @NonNull
    @Override
    public LLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_l_location, parent, false);
        LLocationViewHolder lLocationViewHolder = new LLocationViewHolder(view);
        view.setOnClickListener(v -> {
            lLocationListFormatter.lLocationHighlighter(view, lLocationViewHolder.lLocation);
            lLocationListFormatter.lLocationFormatter(lLocationViewHolder.lLocation);
        });
        return lLocationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LLocationViewHolder holder, int position) {
        holder.lLocation = lLocationsForRecycler.get(position);
        ((TextView) holder.itemView.findViewById(R.id.questLLocationText)).setText(holder.lLocation.name);

    }

    @Override
    public int getItemCount() {
        return lLocationsForRecycler.size();
    }

    public static class LLocationViewHolder extends RecyclerView.ViewHolder{
        public LLocation lLocation;

        public LLocationViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public static interface LLocationListFormatter{
        public void lLocationFormatter(LLocation lLocation);
        public void lLocationHighlighter(View lLocationView, LLocation lLocation);
    }
}
