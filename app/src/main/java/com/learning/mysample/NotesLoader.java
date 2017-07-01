package com.learning.mysample;

import android.content.AsyncTaskLoader;
import android.content.Context;
import com.learning.mysample.database.NotesStorage;

import java.util.List;

/**
 * Created by Диана on 01.07.2017.
 */
public class NotesLoader extends AsyncTaskLoader<List<Note>> implements NotesStorage.IcontentChangeListener{
    private NotesStorage mNotesStorage;
    public NotesLoader(Context context, NotesStorage notesStorage) {
        super(context);
        mNotesStorage = notesStorage;
        mNotesStorage.addListener(this);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(mNotesStorage.getNotes() == null || takeContentChanged()){
            forceLoad();
        }
        else deliverResult(mNotesStorage.getNotes());
    }

    @Override
    public void deliverResult(List<Note> data) {
        super.deliverResult(data);
    }

    @Override
    public List<Note> loadInBackground() {
        return mNotesStorage.getNotes();
    }

    @Override
    protected void onReset() {
        super.onReset();
        mNotesStorage.removeListener(this);
    }

    @Override
    public void changed(NotesStorage sender, Note note) {
        onContentChanged();
    }
}
