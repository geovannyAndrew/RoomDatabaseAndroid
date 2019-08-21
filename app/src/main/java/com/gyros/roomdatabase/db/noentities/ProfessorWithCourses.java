package com.gyros.roomdatabase.db.noentities;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.gyros.roomdatabase.db.entity.Course;
import com.gyros.roomdatabase.db.entity.Professor;

import java.util.List;

public class ProfessorWithCourses {

    @Embedded
    public Professor professor;

    @Relation(
            parentColumn = "id",
            entityColumn = "professor_id",
            entity = Course.class
    )
    public List<Course> courses;
}
