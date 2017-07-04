package com.learning.mysample.notes;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.learning.mysample.*;
import com.learning.mysample.add_note.CreateNoteActivity;
import com.learning.mysample.database.Contract;
import com.learning.mysample.edit_note.EditNoteActivity;

import java.util.List;

/**
 * Created by Диана on 29.06.2017.
 */
public class NotesActivity extends AppCompatActivity {


    public static final String TAG = "ActivityNotes";
    private GridView mGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_layout);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mGridView = (GridView) findViewById(R.id.notes_grid);

        getLoaderManager().initLoader(1,null,new LoaderCallback());
        findViewById(R.id.add_note_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesActivity.this,CreateNoteActivity.class);
                startActivity(intent);
            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = NotesApp.getmNotesStorage().getNoteById(id);
                Intent intent = new Intent(NotesActivity.this, EditNoteActivity.class);
                intent.putExtra(Contract.NotesDbContract.TEXT,note.getmNoteText());
                intent.putExtra(Contract.NotesDbContract.DATE,note.getmDate());
                intent.putExtra(Contract.NotesDbContract.NOTE_COLOR,note.getmNoteTheme());
                intent.putExtra(Contract.NotesDbContract._ID, note.getmId());
                startActivity(intent);

                Log.e(TAG,"id = " + id);

            }
        });



    }
    private class LoaderCallback implements LoaderManager.LoaderCallbacks<List<Note>>{

        @Override
        public Loader<List<Note>> onCreateLoader(int id, Bundle args) {
            return new NotesLoader(NotesActivity.this, NotesApp.getmNotesStorage());
        }

        @Override
        public void onLoadFinished(Loader<List<Note>> loader, List<Note> data) {
            Log.e(TAG,"onLoadFinished"+ " "+ data);
            mGridView.setAdapter(new NotesAdapter(data));
        }

        @Override
        public void onLoaderReset(Loader<List<Note>> loader) {

        }
    }
}
