package com.gyros.roomdatabase.db.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.gyros.roomdatabase.constants.Constants;

@Entity(tableName = Constants.NAME_TABLE_PROFESSOR)
public class Professor {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "email")
    private String email;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
