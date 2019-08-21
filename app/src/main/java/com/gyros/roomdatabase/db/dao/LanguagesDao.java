package com.gyros.roomdatabase.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gyros.roomdatabase.constants.Constants;
import com.gyros.roomdatabase.db.entity.Language;

import java.util.List;

@Dao
public interface LanguagesDao {

    @Insert
    void insert(Language language);

    @Query("SELECT * FROM "+ Constants.NAME_TABLE_LANGUAGES)
    List<Language> getAllLanguages();

    @Update
    void updateLanguage(Language language);

    @Delete
    void deleteLanguage(Language language);
}
