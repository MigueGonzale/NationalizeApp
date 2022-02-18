package com.example.testmenu.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NationalizeTable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "uri")
    public String uri;
}
