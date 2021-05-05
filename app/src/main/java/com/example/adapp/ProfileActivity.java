package com.example.adapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView name;
    private TextView age;
    private TextView bio;
    private TextView occupation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        name = findViewById(R.id.name);
        age =  findViewById(R.id.age);
        bio =  findViewById(R.id.bio);
        occupation = findViewById(R.id.occupation);

        if( b != null) {
            if(b.containsKey((Constants.KEY_NAME))) {
                name.setText(b.getString(Constants.KEY_NAME));
            }

            if(b.containsKey((Constants.KEY_AGE))) {
                StringBuilder str = new StringBuilder(": ");
                str.append(b.getString(Constants.KEY_AGE));
                age.setText(str);
            }

            if(b.containsKey((Constants.KEY_BIO))) {
                bio.setText(b.getString(Constants.KEY_BIO));
            }

            if(b.containsKey((Constants.KEY_OCC))) {
                occupation.setText(b.getString(Constants.KEY_OCC));
            }
        }
        Button backtoMain = findViewById(R.id.back);
        backtoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}