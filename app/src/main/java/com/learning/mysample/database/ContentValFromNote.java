package com.learning.mysample.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import com.learning.mysample.Note;

/**
 * Created by Диана on 30.06.2017.
 */
public class ContentValFromNote {
    public static ContentValues createContentValuesFromNote(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesDbContract.DATE, note.getmDate());
        contentValues.put(NotesDbContract.NOTE_COLOR, note.getmNoteColor());
        contentValues.put(NotesDbContract.TEXT, note.getmNoteText());
        contentValues.put(NotesDbContract._ID, 0);
        return contentValues;
    }

    public static Note createNoteFromCursor(Cursor noteCursor){
        Note note = new Note(getLong(NotesDbContract._ID,noteCursor),
                getString(NotesDbContract.TEXT,noteCursor),
                getLong(NotesDbContract.DATE,noteCursor),
                getString(NotesDbContract.NOTE_COLOR,noteCursor));

      return null;
    }

    public static long getLong(String columnName,Cursor cursor){
        return cursor.getLong(cursor.getColumnIndex(columnName));

    }
    public static String getString(String columnName,Cursor cursor){
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
}
