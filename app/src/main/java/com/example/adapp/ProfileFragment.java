package com.example.adapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class ProfileFragment extends Fragment {

    private SecondActivity.Attachment attachment;
    private FragmentManager manager;
    private TextView name;
    private TextView age;
    private TextView bio;
    private TextView occupation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = view.findViewById(R.id.name);
        bio =  view.findViewById(R.id.bio);
        age =  view.findViewById(R.id.age);
        occupation = view.findViewById(R.id.occupation);

        name.setText(this.attachment.name);
        age.setText(this.attachment.age);
        bio.setText(this.attachment.bio);
        occupation.setText((this.attachment.occ));

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.KEY_NAME, attachment.name);
        outState.putString(Constants.KEY_BIO, attachment.bio);
        outState.putString(Constants.KEY_AGE, attachment.age);
        outState.putString(Constants.KEY_OCC,attachment.occ);
    }


    public void setAttachment(SecondActivity.Attachment attach) {
        this.attachment = attach;
    }
}