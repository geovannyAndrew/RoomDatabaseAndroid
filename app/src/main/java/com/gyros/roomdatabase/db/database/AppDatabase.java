package com.gyros.roomdatabase.db.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.gyros.roomdatabase.constants.Constants;
import com.gyros.roomdatabase.db.dao.CourseDao;
import com.gyros.roomdatabase.db.dao.LanguagesDao;
import com.gyros.roomdatabase.db.dao.ProfessorDao;
import com.gyros.roomdatabase.db.dao.ProfessorLanguageDao;
import com.gyros.roomdatabase.db.dao.ProfessorWithCoursesDao;
import com.gyros.roomdatabase.db.entity.Course;
import com.gyros.roomdatabase.db.entity.Language;
import com.gyros.roomdatabase.db.entity.Professor;
import com.gyros.roomdatabase.db.entity.ProfessorLanguage;

@Database(
        entities = {Professor.class, Course.class, Language.class, ProfessorLanguage.class},
        version = 4
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProfessorDao professorDao();
    public abstract CourseDao courseDao();
    public abstract LanguagesDao languagesDao();
    public abstract ProfessorLanguageDao professorLanguageDao();
    public abstract ProfessorWithCoursesDao professorWithCoursesDao();


    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    Constants.NAME_DATABASE
            )
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .addMigrations(MIGRATION_3_4)
            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE "+Constants.NAME_TABLE_COURSES+" (id INTEGER PRIMARY KEY NOT NULL, name TEXT, duration INTEGER NOT NULL, professor_id INTEGER NOT NULL, foreign key (professor_id) references "+Constants.NAME_TABLE_PROFESSOR+"(id) ON DELETE CASCADE)");
        }
    };


    private static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE "+Constants.NAME_TABLE_LANGUAGES+" (id INTEGER PRIMARY KEY NOT NULL, name TEXT)");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE "+Constants.NAME_TABLE_PROFESSORS_LANGUAGES
                    +"(professor_id INTEGER NOT NULL, language_id INTEGER NOT NULL, PRIMARY KEY (professor_id, language_id), FOREIGN KEY (professor_id) REFERENCES "+Constants.NAME_TABLE_PROFESSOR+"(id), FOREIGN KEY (language_id) REFERENCES "+Constants.NAME_TABLE_LANGUAGES+"(id))");
        }
    };
}


