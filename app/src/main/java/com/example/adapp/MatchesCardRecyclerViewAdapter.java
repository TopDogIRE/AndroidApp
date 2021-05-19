package com.example.adapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.adapp.model.Match;
import com.example.adapp.network.ImageRequester;
import java.util.List;

public class MatchesCardRecyclerViewAdapter extends RecyclerView.Adapter<MatchesCardViewHolder> {

    private List<Match> matchesList;
    private ImageRequester imageRequester;

    MatchesCardRecyclerViewAdapter(List<Match> matchesList) {
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
        if (matchesList != null) {
            Match m = this.matchesList.get(position);
            holder.name.setText(m.name);
            holder.m = m;
            holder.setLiked(m.liked);
            imageRequester.setImageFromUrl(holder.matchImage, m.imageUrl);
        }
    }

    @Override
    public int getItemCount () {
        return matchesList.size();
    }

    public void setMatchesList(List<Match> m) {
        this.matchesList = m;
    }
}