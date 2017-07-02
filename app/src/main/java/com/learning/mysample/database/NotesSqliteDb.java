package com.learning.mysample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.learning.mysample.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Диана on 30.06.2017.
 */
public class NotesSqliteDb extends SQLiteOpenHelper{


    public static final int VERSION = 1;
    public static final String TAG = "Bd";

    public NotesSqliteDb(Context context) {

        super(context, null,null, VERSION);
        Log.e(TAG,"NoteSqliteDB");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            String createDbSql = "CREATE TABLE " +
                    NotesDbContract.TABLE_NAME + "(" +
                    NotesDbContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    NotesDbContract.DATE + " INTEGER NOT NULL, " +
                    NotesDbContract.TEXT + " TEXT, " +
                    NotesDbContract.NOTE_COLOR + " INTEGER NOT NULL " +
                    ");";
            db.execSQL(createDbSql);
           Log.e(TAG,"onCreate "+createDbSql);


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
                Log.e(TAG,ContentValFromNote.createNoteFromCursor(cursor).toString());
                noteList.add(ContentValFromNote.createNoteFromCursor(cursor));
                cursor.moveToNext();
            }
            database.setTransactionSuccessful();
        }
        finally {
            if(cursor != null) cursor.close();
            database.endTransaction();

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

        }
        Log.e(TAG,"id = "+ insertedId);
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

        }
        return updated;
    }
    protected int removeNote(Note note){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        int deletedNotes = 0;
        try {
            deletedNotes = db.delete(NotesDbContract.TABLE_NAME,
                    NotesDbContract._ID + " = ? ",
                    new String[]{String.valueOf(note.getmId())});
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
        return deletedNotes;
    }
    protected Note getNoteById(long id){
        SQLiteDatabase database = getReadableDatabase();
        database.beginTransaction();
        Note note = null;
        Cursor cursor = null;
        try{
            cursor = database.query(NotesDbContract.TABLE_NAME,
                    new String[]{NotesDbContract._ID,NotesDbContract.TEXT,NotesDbContract.DATE,NotesDbContract.NOTE_COLOR},
                    NotesDbContract._ID + " = ?",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    null);
            Log.e(TAG,"getNodeById id= "+ id);
            cursor.moveToFirst();

            note = ContentValFromNote.createNoteFromCursor(cursor);
            //Log.e(TAG, "not = " + note);
            database.setTransactionSuccessful();
        }
        finally {
            database.endTransaction();

            if(cursor != null){
                cursor.close();
            }
        }
        return note;
    }
}
