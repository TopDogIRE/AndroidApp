package com.example.adapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText mTextView;
    private TextView dob;
    private EditText userName;
    private TextView age;
    private EditText bio;
    private EditText occupation;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.text);

        dob = findViewById(R.id.dob);
        Button btPickDate = findViewById(R.id.btPickDate);
        btPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.adapp.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new com.example.adapp.DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });

    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState.containsKey(Constants.KEY_TEXTVIEW_TEXT)) {
            mTextView.setText(savedInstanceState.getString(Constants.KEY_TEXTVIEW_TEXT));
        }

        if(savedInstanceState.containsKey(Constants.KEY_AGE)) {
            dob.setText(savedInstanceState.getString(Constants.KEY_AGE));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState,outPersistentState);

        outState.putString(Constants.KEY_TEXTVIEW_TEXT, mTextView.getText().toString());
        outState.putString(Constants.KEY_AGE,dob.getText().toString());

    }

    public void submitForm(View view) {
        mTextView = findViewById(R.id.name);
        userName = findViewById(R.id.username);
        email = findViewById(R.id.email);
        age = findViewById(R.id.dob);
        bio = findViewById(R.id.bio);
        occupation = findViewById(R.id.occupation);

        if(!isValidEmail(email.getText())){
            email.setError("Email not valid!");
            return;
        }

        if(mTextView.getText().toString().matches("")){
            mTextView.setError("Cannot Be Blank!");
            return;
        }

        if(userName.getText().toString().matches("")){
            userName.setError("Cannot Be Blank!");
            return;
        }

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();


        bundle.putString(Constants.KEY_NAME, mTextView.getText().toString());
        bundle.putString(Constants.KEY_AGE, age.getText().toString());
        bundle.putString(Constants.KEY_BIO, bio.getText().toString());
        bundle.putString(Constants.KEY_OCC, occupation.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        Date eighteen = new Date(1050871680000L);
        Button b = findViewById(R.id.button2);
        dob = findViewById(R.id.dob);
        Calendar mCalender = Calendar.getInstance();
        mCalender.set(Calendar.YEAR, year);
        mCalender.set(Calendar.MONTH, month);
        mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        Date d = new Date();

        Long LAge = Math.subtractExact(d.getTime(), mCalender.getTime().getTime());
        int age = (int) Long.divideUnsigned(LAge, 31557600000L);
        StringBuilder str = new StringBuilder();
        str.append(age);
        str.append(" Years Old.");

        if(mCalender.getTime().compareTo(eighteen) > 0) {
            dob.setError("Must Be Older Than 18!");
            b.setEnabled(false);
        } else {
            dob.setText(str);
            b.setEnabled(true);
        }

    }
}