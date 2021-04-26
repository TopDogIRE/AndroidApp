package com.example.adapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }
    public void onClickFormSubmit(View view){
        Intent intent = new Intent(FormActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
