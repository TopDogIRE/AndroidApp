package com.example.adapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        name = findViewById(R.id.name);


        if( b != null) {
            if(b.containsKey((Constants.KEY_NAME))) {
                name.setText("Welcome " + b.getString(Constants.KEY_NAME));
            }
        }

    }

}
