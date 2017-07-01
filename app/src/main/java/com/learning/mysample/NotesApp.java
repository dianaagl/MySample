package com.learning.mysample;

import android.app.Application;
import android.util.Log;
import com.learning.mysample.database.NotesSqliteDb;
import com.learning.mysample.database.NotesStorage;

/**
 * Created by Диана on 01.07.2017.
 */
public class NotesApp extends Application {

    public static final String NOTES_APP = "NotesApp";
    private static NotesStorage mNotesStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        NotesSqliteDb sqliteDb = new NotesSqliteDb(this);
        mNotesStorage = new NotesStorage(sqliteDb);
        Log.e(NOTES_APP,"onCreate");
    }


    public static NotesStorage getmNotesStorage() {
        return mNotesStorage;
    }
}
