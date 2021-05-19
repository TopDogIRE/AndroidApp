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
        if(savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.KEY_NAME)
                    && savedInstanceState.containsKey(Constants.KEY_AGE)
                    && savedInstanceState.containsKey(Constants.KEY_BIO)
                    && savedInstanceState.containsKey(Constants.KEY_OCC)) {
                String name = savedInstanceState.getString(Constants.KEY_NAME);
                String age = savedInstanceState.getString(Constants.KEY_AGE);
                String bio = savedInstanceState.getString(Constants.KEY_BIO);
                String occ = savedInstanceState.getString(Constants.KEY_OCC);

                SecondActivity.Attachment a = new SecondActivity.Attachment(name,age,bio,occ);
                this.setAttachment(a);
            }
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        outState.putString(Constants.KEY_NAME, this.attachment.name);
        outState.putString(Constants.KEY_BIO, this.attachment.bio);
        outState.putString(Constants.KEY_AGE, this.attachment.age);
        outState.putString(Constants.KEY_OCC, this.attachment.occ);
    }

    void setAttachment(SecondActivity.Attachment attach) {
        this.attachment = attach;
    }
}