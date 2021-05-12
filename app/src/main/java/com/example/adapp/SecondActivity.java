package com.example.adapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,MyListener {

    private FragmentManager manager;
    private DrawerLayout drawer;
    private String name;
    private String age;
    private String bio;
    private String occ;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        manager = getSupportFragmentManager();
        intent = getIntent();
        Bundle b = intent.getExtras();

        if(b != null) {
            if (b.containsKey(Constants.KEY_NAME)
                    && b.containsKey(Constants.KEY_AGE)
                    && b.containsKey(Constants.KEY_BIO)
                    && b.containsKey(Constants.KEY_OCC)) {
                name = b.getString(Constants.KEY_NAME);
                age = b.getString(Constants.KEY_AGE);
                bio = b.getString(Constants.KEY_BIO);
                occ = b.getString(Constants.KEY_OCC);
            }
        }


        ProfileFragment fragment = new ProfileFragment();
        fragment.setAttachment(new Attachment(name, age, bio, occ));

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment, "fragA");
        transaction.commit();
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                ProfileFragment fragment = new ProfileFragment();
                fragment.setAttachment(new Attachment(name, age, bio, occ));

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        fragment).commit();
                break;
            case R.id.nav_matches:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MatchesFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
        }
        return true;
    }

    @Override
    public void matchesLikeToast(String n) {
        Toast.makeText(this,String.format("You Liked " + n ),Toast.LENGTH_LONG).show();
    }


    public static class Attachment {
        String name;
        String age;
        String bio;
        String occ;

        Attachment(String name, String age, String bio, String occ) {
            this.name = name;
            this.age = age;
            this.bio = bio;
            this.occ = occ;
        }

    }
}