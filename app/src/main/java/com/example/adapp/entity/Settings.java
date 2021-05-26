package com.example.adapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Settings {
    @PrimaryKey(autoGenerate = true)
    private int settingID;

    @ColumnInfo(name = "reminder_time")
    private int reminderTime;

    @ColumnInfo(name = "max_distance")
    private int maxDist;

    @ColumnInfo(name = "gender")
    private String gender; //0 for male, 1 for female, 3 for non-binary

    @ColumnInfo(name = "private_account")
    private boolean privateAcct;

    @ColumnInfo(name="min_age")
    private int minAge;

    @ColumnInfo(name="max_age")
    private int maxAge;

    public void setSettingID(int settingID) {
        this.settingID = settingID;
    }

    public int getSettingID() {
        return settingID;
    }

    public int getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(int reminderTime) {
        this.reminderTime = reminderTime;
    }

    public int getMaxDist() {
        return maxDist;
    }

    public void setMaxDist(int maxDist) {
        this.maxDist = maxDist;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPrivateAcct(boolean privateAcct) {
        this.privateAcct = privateAcct;
    }

    public boolean isPrivateAcct() {
        return privateAcct;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}