package com.learning.mysample.database;

import android.util.Log;
import com.learning.mysample.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Диана on 30.06.2017.
 */
public class NotesStorage {

    public static final String TAG = "NotesStotage";
    private NotesSqliteDb notesDb ;
    private List<IcontentChangeListener> mListenerList = new ArrayList<>();

    public NotesStorage(NotesSqliteDb notesDb) {
        this.notesDb = notesDb;
    }
    public List<Note> getNotes(){
        //Log.e(TAG,"geNotes");
        List<Note> notes = new ArrayList<>(notesDb.getNotes());
        return notes;
    }
    public boolean insertNote(Note note){
        //Log.e(TAG,"insert");
        boolean isInserted = false;
        long id = notesDb.insertNote(note);
        if(id != -1){
            isInserted = true;
            for(IcontentChangeListener listener:mListenerList){
                listener.changed(this,note);
            }
        }

        return isInserted;
    }
    public boolean updateNote(Note note){
        //Log.e(TAG,"update");
        boolean isUpdated = false;
        int updatesCount = notesDb.updateNote(note);
        if(updatesCount > 0){
            isUpdated = true;
            for(IcontentChangeListener listener:mListenerList){
                listener.changed(this,note);
            }
        }
        return isUpdated;
    }
    public boolean deleteNote(Note note){
        int deletedNotes = notesDb.removeNote(note);
        boolean isDeleted = false;
        if(deletedNotes == 1) {

            isDeleted = true;
            for (IcontentChangeListener listener : mListenerList) {
                listener.changed(this, note);
            }
        }
        return isDeleted;
    }
    public Note getNoteById(long id){

        return notesDb.getNoteById(id);
    }
    public Note getLastNote(){

        return notesDb.getLastNote();
    }
    public void addListener(IcontentChangeListener listener){
        //Log.e(TAG,"addlistener");
        mListenerList.add(listener);

    }
    public void removeListener(IcontentChangeListener listener){
        //Log.e(TAG,"removelistener");
        mListenerList.remove(listener);
    }
    public interface IcontentChangeListener{
        public void changed(NotesStorage sender,Note note);
    }
}
