package com.example.adapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.adapp.entity.Settings;

import java.util.List;

@Dao
public interface SettingDao {

    @Query("SELECT * FROM settings" )
    LiveData<List<Settings>> getSettings();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveSettings(Settings... settings);

}