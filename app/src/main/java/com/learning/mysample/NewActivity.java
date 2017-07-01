package com.learning.mysample;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Диана on 29.06.2017.
 */
public class NewActivity extends Activity {
    private List<Note> mNotesList = new ArrayList();
    private NotesAdapter mNotesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_layout);
        mNotesAdapter = new NotesAdapter();
        getLoaderManager().initLoader(1,null,new LoaderCallback());
        findViewById(R.id.add_note_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewActivity.this,CreateNoteActivity.class);
                startActivity(intent);
            }
        });
        GridView gridView = (GridView) findViewById(R.id.notes_grid);
        gridView.setAdapter(mNotesAdapter);

    }
    private class LoaderCallback implements LoaderManager.LoaderCallbacks<List<Note>>{

        @Override
        public Loader<List<Note>> onCreateLoader(int id, Bundle args) {
            return new NotesLoader(NewActivity.this, NotesApp.getmNotesStorage());
        }

        @Override
        public void onLoadFinished(Loader<List<Note>> loader, List<Note> data) {
            mNotesAdapter.setNotes(data);
        }

        @Override
        public void onLoaderReset(Loader<List<Note>> loader) {

        }
    }
}
