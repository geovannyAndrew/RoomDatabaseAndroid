package com.gyros.roomdatabase.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gyros.roomdatabase.constants.Constants;
import com.gyros.roomdatabase.db.entity.Course;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    void insert(Course course);

    @Query("SELECT * FROM "+ Constants.NAME_TABLE_COURSES+" WHERE professor_id =:professorId")
    List<Course> findCoursesByProfessor(int professorId);

    @Update
    void updateCourseById(Course course);

    @Delete
    void deleteCourseById(Course course);
}
