package com.example.adapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        username = findViewById(R.id.welcomeName);
        username.setText(getIntent().getStringExtra("username"));

    }

    public void onClickWelcomeToForm(View view){
        Intent intent = new Intent(WelcomeActivity.this, FormActivity.class);
        startActivity(intent);
    }
}
