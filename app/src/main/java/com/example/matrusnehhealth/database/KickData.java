package com.example.matrusnehhealth.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class KickData {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int count;

    public String time;
}