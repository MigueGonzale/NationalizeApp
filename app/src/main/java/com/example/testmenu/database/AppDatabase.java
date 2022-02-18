package com.example.testmenu.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.testmenu.interfaces.NationalizeDao;

/**
 * This Class create the database
 */
@Database(entities = {NationalizeTable.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NationalizeDao nationalizeDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {
        String dbName = "nationalizeDb"; // Name Db
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }

        return INSTANCE;
    }
}
