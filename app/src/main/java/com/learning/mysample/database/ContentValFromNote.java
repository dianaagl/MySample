package com.learning.mysample.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.util.Log;
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

        return contentValues;
    }
    public static Note createNoteFromCursor(Cursor noteCursor){
        Log.e("TAG",getLong(NotesDbContract._ID, noteCursor) + " = id");
        Note note = new Note(getLong(NotesDbContract._ID, noteCursor),
                getString(NotesDbContract.TEXT,noteCursor),
                getLong(NotesDbContract.DATE,noteCursor),
                getString(NotesDbContract.NOTE_COLOR,noteCursor));


        return note;
    }

    public static Note createNoteFromCursor(Cursor noteCursor,long id){
//        Log.e("TAG",getLong(NotesDbContract._ID, noteCursor) + " = id");
        Note note = new Note(getLong(NotesDbContract._ID,noteCursor),
                getString(NotesDbContract.TEXT,noteCursor),
                getLong(NotesDbContract.DATE,noteCursor),
                getString(NotesDbContract.NOTE_COLOR,noteCursor));


      return note;
    }

    public static long getLong(String columnName,Cursor cursor){
        Log.e("TAG",columnName);
        return cursor.getLong(cursor.getColumnIndex(columnName));

    }
    public static String getString(String columnName,Cursor cursor){
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
}
