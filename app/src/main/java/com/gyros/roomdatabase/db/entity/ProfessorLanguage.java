package com.gyros.roomdatabase.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.gyros.roomdatabase.constants.Constants;

@Entity(
        tableName = Constants.NAME_TABLE_PROFESSORS_LANGUAGES,
        primaryKeys = { "professor_id", "language_id" },
        foreignKeys = {
                @ForeignKey(
                        entity = Professor.class,
                        parentColumns = "id",
                        childColumns = "professor_id"
                ),
                @ForeignKey(
                        entity = Language.class,
                        parentColumns = "id",
                        childColumns = "language_id"
                )
        }
)
public class ProfessorLanguage {

    @ColumnInfo(name = "professor_id")
    private int professorId;

    @ColumnInfo(name = "language_id")
    private int languageId;

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
}
