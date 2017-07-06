package com.learning.mysample.notes;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.learning.mysample.Note;
import com.learning.mysample.NotesWidget;
import com.learning.mysample.database.NotesStorage;

import java.util.List;

/**
 * Created by Диана on 01.07.2017.
 */
public class NotesLoader extends AsyncTaskLoader<List<Note>> implements NotesStorage.IcontentChangeListener{
    private static final String TAG = "Loader";
    private NotesStorage mNotesStorage;
    public NotesLoader(Context context, NotesStorage notesStorage) {
        super(context);
        mNotesStorage = notesStorage;
        mNotesStorage.addListener(this);
        //Log.e(TAG,"NotesLoader");
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
        //Log.e(TAG,"onStartLoading()");

    }

    @Override
    public void deliverResult(List<Note> data) {
        //Log.e(TAG,"deliverResult");
        super.deliverResult(data);
    }

    @Override
    public List<Note> loadInBackground()
    {      //Log.e(TAG,"loadInBg " + mNotesStorage.getNotes());
        return mNotesStorage.getNotes();
    }

    @Override
    protected void onReset() {
        //Log.e(TAG,"reset");
        super.onReset();
        mNotesStorage.removeListener(this);
    }

    @Override
    public void changed(NotesStorage sender, Note note) {
        //Log.e(TAG,"onCOntentChanged");
        Intent intent = new Intent(getContext(),NotesWidget.MyService.class);
        getContext().startService(intent);
        onContentChanged();


    }
}
