package com.cinnamontoast.scavengerhunt.adapters;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Location;
import com.cinnamontoast.scavengerhunt.R;
import com.cinnamontoast.scavengerhunt.activities.CreateQuestActivity;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    ArrayList<Location> locationForRecycler;
    LocationListFormatter locationListFormatter;

    public LocationAdapter(ArrayList<Location> locationForRecycler, LocationListFormatter locationListFormatter) {
        this.locationForRecycler = locationForRecycler;
        this.locationListFormatter = locationListFormatter;
    }


    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_location, parent, false);
        LocationViewHolder locationViewHolder = new LocationViewHolder(view);
        view.setOnClickListener(v -> {
            locationListFormatter.locationFormatter(locationViewHolder.location);

        });
        return locationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.location = locationForRecycler.get(position);
        ((TextView) holder.itemView.findViewById(R.id.locationName)).setText(holder.location.getName());

    }

    @Override
    public int getItemCount() {
        return locationForRecycler.size();
    }

    public static class LocationViewHolder extends RecyclerView.ViewHolder {
        public Location location;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static interface LocationListFormatter {
        public void locationFormatter(Location location);
    }

}