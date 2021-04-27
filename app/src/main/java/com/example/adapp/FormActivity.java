package com.example.adapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class FormActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText username;
    private DatePicker dateOB;
    private Button signupBT;
    private long currentDate;
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    private Pattern mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        currentDate = new Date().getTime(); // The current Date

        name = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        dateOB = findViewById(R.id.dateOfBirth);
        dateOB.setMaxDate(currentDate);
        signupBT = findViewById(R.id.register);

    }

    public void onClickFormSubmit(View view) throws ParseException {
        boolean mailMatch = Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();

        int birthday = dateOB.getDayOfMonth();
        int birthmonth = dateOB.getMonth();
        int birthyear = dateOB.getYear();
        String strBirthdate = birthday + "/" + birthmonth + "/" + birthyear;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = sdf.parse(strBirthdate);
        Instant instant = birthDate.toInstant();
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
        LocalDate givenDate = zone.toLocalDate();
        LocalDate local = LocalDate.now(ZoneId.of("America/Vancouver"));
        Period period = Period.between(givenDate, local);
        boolean over18 = period.getYears() >= 18;

        boolean na = (name.getText().toString().length() > 0) && (mailMatch) && (username.getText().toString().length() > 0) && over18;
        if(na){
            Intent intent = new Intent(FormActivity.this, WelcomeActivity.class);
            intent.putExtra("welcomingName", "Hello " + username.getText().toString());
            startActivity(intent);
        }else{
            Toast.makeText(FormActivity.this, "Missing field or under 18", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickFormToHome(View view){
        Intent intent = new Intent(FormActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
