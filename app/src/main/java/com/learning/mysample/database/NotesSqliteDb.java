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
    public static final String NAME = "notes.db";
    public static final int NO_ID = -1;

    public NotesSqliteDb(Context context) {

        super(context, NAME,null, VERSION);
        Log.e(TAG,"NoteSqliteDB");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            String createDbSql = "CREATE TABLE " +
                    Contract.NotesDbContract.TABLE_NAME + "(" +
                    Contract.NotesDbContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    Contract.NotesDbContract.DATE + " INTEGER NOT NULL, " +
                    Contract.NotesDbContract.TEXT + " TEXT, " +
                    Contract.NotesDbContract.NOTE_COLOR + " INTEGER NOT NULL " +
                    ");";
            db.execSQL(createDbSql);
           Log.e(TAG,"onCreate "+createDbSql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    protected Note getLastNote(){
        SQLiteDatabase db = getReadableDatabase();
        db.beginTransaction();
        Cursor cursor = null;
        Note note = null;
        try {
            cursor = db.query (Contract.NotesDbContract.TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                   Contract.NotesDbContract.DATE) ;
            cursor.moveToLast();
            if(!cursor.isAfterLast()) {
                note = ContentValFromNote.createNoteFromCursor(cursor);
            }
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
            if(cursor != null){
                cursor.close();
            }
        }
        return note;
    }
    protected List<Note> getNotes(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = null;
        List<Note> noteList = new ArrayList<>();
        database.beginTransaction();
        try{
            cursor = database.query(
                    Contract.NotesDbContract.TABLE_NAME,
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
        long insertedId = NO_ID;
        try {
            ContentValues contentValues = ContentValFromNote.createContentValuesFromNote(note);

            insertedId = database.insert(Contract.NotesDbContract.TABLE_NAME, null, contentValues);
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

            updated = database.update(Contract.NotesDbContract.TABLE_NAME,
                    contentValues,
                    Contract.NotesDbContract._ID +" = ? ",
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
            deletedNotes = db.delete(Contract.NotesDbContract.TABLE_NAME,
                    Contract.NotesDbContract._ID + " = ? ",
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
            cursor = database.query(Contract.NotesDbContract.TABLE_NAME,
                    new String[]{Contract.NotesDbContract._ID,Contract.NotesDbContract.TEXT,Contract.NotesDbContract.DATE,Contract.NotesDbContract.NOTE_COLOR},
                    Contract.NotesDbContract._ID + " = ?",
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
