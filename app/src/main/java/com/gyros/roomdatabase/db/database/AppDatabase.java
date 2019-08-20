package com.gyros.roomdatabase.db.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gyros.roomdatabase.constants.Constants;
import com.gyros.roomdatabase.db.dao.ProfessorDao;
import com.gyros.roomdatabase.db.entity.Professor;

@Database(
        entities = {Professor.class },
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProfessorDao professorDao();


    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    Constants.NAME_DATABASE
            ).build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}


