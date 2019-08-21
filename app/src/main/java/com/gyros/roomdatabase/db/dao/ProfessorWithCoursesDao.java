package com.gyros.roomdatabase.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.gyros.roomdatabase.constants.Constants;
import com.gyros.roomdatabase.db.entity.Professor;
import com.gyros.roomdatabase.db.noentities.ProfessorWithCourses;

import java.util.List;

@Dao
public interface ProfessorWithCoursesDao {

    @Query("SELECT * FROM "+ Constants.NAME_TABLE_PROFESSOR)
    List<ProfessorWithCourses> getProfessors();
}
