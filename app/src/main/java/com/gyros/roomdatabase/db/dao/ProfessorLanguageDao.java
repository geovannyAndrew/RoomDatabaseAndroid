package com.gyros.roomdatabase.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gyros.roomdatabase.constants.Constants;
import com.gyros.roomdatabase.db.entity.Language;
import com.gyros.roomdatabase.db.entity.Professor;
import com.gyros.roomdatabase.db.entity.ProfessorLanguage;

import java.util.List;

@Dao
public interface ProfessorLanguageDao {

    @Insert
    void insert(ProfessorLanguage professorLanguage);

    @Query("SELECT * FROM "+ Constants.NAME_TABLE_PROFESSOR+
            " INNER JOIN "+Constants.NAME_TABLE_PROFESSORS_LANGUAGES
            +" ON "+Constants.NAME_TABLE_PROFESSOR+".id = "+Constants.NAME_TABLE_PROFESSORS_LANGUAGES+".professor_id WHERE "
            +Constants.NAME_TABLE_PROFESSORS_LANGUAGES+".language_id = :idLanguage")
    List<Professor> getProfessorsByLanguage(int idLanguage);

    @Query("SELECT * FROM "+ Constants.NAME_TABLE_LANGUAGES+
            " INNER JOIN "+Constants.NAME_TABLE_PROFESSORS_LANGUAGES
            +" ON "+Constants.NAME_TABLE_LANGUAGES+".id = "+Constants.NAME_TABLE_PROFESSORS_LANGUAGES+".language_id WHERE "
            +Constants.NAME_TABLE_PROFESSORS_LANGUAGES+".professor_id = :idProfessor")
    List<Language> getLanguagesByProfessor(int idProfessor);


    @Query("SELECT * FROM "+ Constants.NAME_TABLE_PROFESSORS_LANGUAGES)
    List<ProfessorLanguage> getAllProfessorsLanguages();
}
