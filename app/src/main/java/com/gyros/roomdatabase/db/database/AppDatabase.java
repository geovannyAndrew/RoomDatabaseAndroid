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
import com.gyros.roomdatabase.db.dao.ProfessorDao;
import com.gyros.roomdatabase.db.entity.Course;
import com.gyros.roomdatabase.db.entity.Professor;

@Database(
        entities = {Professor.class, Course.class},
        version = 2
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProfessorDao professorDao();
    public abstract CourseDao courseDao();


    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    Constants.NAME_DATABASE
            )
            .addMigrations(MIGRATION_1_2)
            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE "+Constants.NAME_TABLE_COURSES+" (id INTEGER PRIMARY KEY NOT NULL, name TEXT, duration INTEGER NOT NULL, professor_id INTEGER NOT NULL, foreign key (professor_id) references "+Constants.NAME_TABLE_PROFESSOR+"(id) ON DELETE CASCADE)");
        }
    };
}


