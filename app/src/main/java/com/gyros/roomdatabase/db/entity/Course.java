package com.gyros.roomdatabase.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.gyros.roomdatabase.constants.Constants;

@Entity(
        tableName = Constants.NAME_TABLE_COURSES,
        foreignKeys = @ForeignKey(
                entity = Professor.class ,
                parentColumns = "id",
                childColumns = "professor_id",
                onDelete = ForeignKey.CASCADE
        )
)
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "duration")
    private int duration;


    @ColumnInfo(name = "professor_id")
    private int professorId;

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
}
