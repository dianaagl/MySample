package com.learning.mysample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.learning.mysample.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Диана on 30.06.2017.
 */
public class NotesSqliteDb extends SQLiteOpenHelper{


    public static final int VERSION = 1;

    public NotesSqliteDb(Context context) {
        super(context, null,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDbSql = "CREATE TABLE " +
                NotesDbContract.TABLE_NAME +  "(" +
                NotesDbContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                NotesDbContract.DATE + " INTEGER NOT NULL, "+
                NotesDbContract.TEXT + " TEXT, "+
                NotesDbContract.NOTE_COLOR + " TEXT NOT NULL " +
                ");";
        db.execSQL(createDbSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    protected List<Note> getNotes(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = null;
        List<Note> noteList = new ArrayList<>();
        database.beginTransaction();
        try{
            cursor = database.query(
                    NotesDbContract.TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                noteList.add(ContentValFromNote.createNoteFromCursor(cursor));
                cursor.moveToNext();
            }
            database.setTransactionSuccessful();
        }
        finally {
            if(cursor != null) cursor.close();
            database.endTransaction();
            database.close();
        }
        return noteList;
    }
    protected long insertNote(Note note){
        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();
        long insertedId = -1;
        try {
            ContentValues contentValues = ContentValFromNote.createContentValuesFromNote(note);

            insertedId = database.insert(NotesDbContract.TABLE_NAME, null, contentValues);
            database.setTransactionSuccessful();
        }
        finally {
            database.endTransaction();
            database.close();
        }
        return insertedId;
    }

    protected int updateNote(Note note){
        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();
        int updated = -1;
        try {
            ContentValues contentValues = ContentValFromNote.createContentValuesFromNote(note);

            updated = database.update(NotesDbContract.TABLE_NAME,
                    contentValues,
                    NotesDbContract._ID +" = ? ",
                    new String[]{String.valueOf(note.getmId())}
            );
            database.setTransactionSuccessful();
        }
        finally {
            database.endTransaction();
            database.close();
        }
        return updated;
    }

    protected Note getNoteById(long id){
        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();
        Note note = null;
        Cursor cursor = null;
        try{
            cursor = database.query(NotesDbContract.TABLE_NAME,
                    null,
                    NotesDbContract._ID + " = ?",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    null);
            note = ContentValFromNote.createNoteFromCursor(cursor);
            database.setTransactionSuccessful();
        }
        finally {
            database.endTransaction();
            database.close();
            if(cursor != null){
                cursor.close();
            }
        }
        return note;
    }
}
