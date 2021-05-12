package com.example.adapp;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

public class MatchesCardViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView matchImage;
    public TextView name;

    public MatchesCardViewHolder(@NonNull View itemView) {
        super(itemView);
        matchImage = itemView.findViewById(R.id.match_image);
        name = itemView.findViewById(R.id.match_name);
        Button likeBtn = itemView.findViewById(R.id.like_button);
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyListener myListener = (MyListener) v.getContext();
                if (myListener != null) {
                    myListener.matchesLikeToast(name.getText().toString());
                }
            }
        });
    }


}