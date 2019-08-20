package com.gyros.roomdatabase.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gyros.roomdatabase.constants.Constants;
import com.gyros.roomdatabase.db.entity.Professor;

import java.util.List;

@Dao
public interface ProfessorDao {

    @Insert
    void insertProfessor(Professor professor);

    @Query("SELECT * FROM "+ Constants.NAME_TABLE_PROFESSOR)
    List<Professor> getAllProfessors();

    @Query("SELECT * FROM "+Constants.NAME_TABLE_PROFESSOR+" WHERE name LIKE :name")
    List<Professor> findProfessorsByName(String name);

    @Query("SELECT * FROM "+Constants.NAME_TABLE_PROFESSOR+" WHERE id = :id")
    Professor findProfessorById(int id);

    @Update
    void updateProfessorById(Professor professor);

    @Query("DELETE FROM "+Constants.NAME_TABLE_PROFESSOR)
    void deleteAllProfessors();

    @Delete
    void deleteProfessorById(Professor professor);
}
