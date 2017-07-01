package com.learning.mysample.database;

import com.learning.mysample.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Диана on 30.06.2017.
 */
public class NotesStorage {
    private NotesSqliteDb notesDb ;
    private List<IcontentChangeListener> mListenerList = new ArrayList<>();

    public NotesStorage(NotesSqliteDb notesDb) {
        this.notesDb = notesDb;
    }
    public List<Note> getNotes(){
        List<Note> notes = new ArrayList<>(notesDb.getNotes());
        return notes;
    }
    public boolean insertNote(Note note){
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
    public void addListener(IcontentChangeListener listener){
        mListenerList.add(listener);

    }
    public void removeListener(IcontentChangeListener listener){
        mListenerList.remove(listener);
    }
    public interface IcontentChangeListener{
        public void changed(NotesStorage sender,Note note);
    }
}
