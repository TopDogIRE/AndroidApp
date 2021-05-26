package com.example.adapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.adapp.dao.SettingDao;
import com.example.adapp.entity.Settings;

@Database(entities = {Settings.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SettingDao settingsDao();
}