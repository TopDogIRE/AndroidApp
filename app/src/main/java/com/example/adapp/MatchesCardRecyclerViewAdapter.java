package com.example.adapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapp.network.ImageRequester;
import com.example.adapp.network.MatchesEntry;

import java.util.List;

/**
 * Adapter used to show a simple grid of products.
 */
public class MatchesCardRecyclerViewAdapter extends RecyclerView.Adapter<MatchesCardViewHolder> {

    private List<MatchesEntry> matchesList;
    private ImageRequester imageRequester;

    MatchesCardRecyclerViewAdapter(List<MatchesEntry> matchesList) {
        this.matchesList = matchesList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public MatchesCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_card, parent, false);
        return new MatchesCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesCardViewHolder holder, int position) {
        if (matchesList != null && position < matchesList.size()) {
            MatchesEntry product = matchesList.get(position);
            holder.name.setText(product.name);
            imageRequester.setImageFromUrl(holder.matchImage, product.url);
        }
    }


    @Override
    public int getItemCount () {
        return matchesList.size();
    }
}