package com.learning.mysample.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import com.learning.mysample.Note;

/**
 * Created by Диана on 30.06.2017.
 */
public class ContentValFromNote {
    public static ContentValues createContentValuesFromNote(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.NotesDbContract.DATE, note.getmDate());
        contentValues.put(Contract.NotesDbContract.NOTE_COLOR, note.getmNoteTheme());
        contentValues.put(Contract.NotesDbContract.TEXT, note.getmNoteText());

        return contentValues;
    }
    public static Note createNoteFromCursor(Cursor noteCursor){
        Note note = new Note(getLong(Contract.NotesDbContract._ID, noteCursor),
                getString(Contract.NotesDbContract.TEXT,noteCursor),
                getLong(Contract.NotesDbContract.DATE,noteCursor),
                getInt(Contract.NotesDbContract.NOTE_COLOR,noteCursor));


        return note;
    }

    public static Note createNoteFromCursor(Cursor noteCursor,long id){
//        Log.e("TAG",getLong(Contract.NotesDbContract._ID, noteCursor) + " = id");
        Note note = new Note(getLong(Contract.NotesDbContract._ID,noteCursor),
                getString(Contract.NotesDbContract.TEXT,noteCursor),
                getLong(Contract.NotesDbContract.DATE,noteCursor),
                getInt(Contract.NotesDbContract.NOTE_COLOR,noteCursor));
      return note;
    }

    public static long getLong(String columnName,Cursor cursor){
        return cursor.getLong(cursor.getColumnIndex(columnName));

    }
    public static String getString(String columnName,Cursor cursor){
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
    public static int getInt(String columnName,Cursor cursor){
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
}
