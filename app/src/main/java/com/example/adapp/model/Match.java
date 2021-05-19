package com.example.adapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Match implements Parcelable {
    public String uid;
    public String name;
    public boolean liked;
    public String imageUrl;

    public Match(){

    }

    public Match(String name, boolean liked, String imageUrl) {
        this.name = name;
        this.liked = liked;
        this.imageUrl = imageUrl;
    }

    public Match(Parcel in) {
        name = in.readString();
        liked = in.readByte() != 0;
        imageUrl = in.readString();
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (liked ? 1 : 0));
        dest.writeString(imageUrl);
    }
}