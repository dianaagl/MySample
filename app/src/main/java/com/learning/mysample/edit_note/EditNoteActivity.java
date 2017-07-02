package com.learning.mysample.edit_note;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.learning.mysample.Note;
import com.learning.mysample.NotesApp;
import com.learning.mysample.R;
import com.learning.mysample.database.Contract;
import com.learning.mysample.database.Contract.NotesDbContract;

/**
 * Created by Диана on 01.07.2017.
 */

public class EditNoteActivity extends AppCompatActivity {
    private  AppCompatEditText mEditText;
    private Note mCacheNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        Intent intent = getIntent();
        mCacheNote = new Note(
                intent.getLongExtra(Contract.NotesDbContract._ID,-1),
                intent.getStringExtra(Contract.NotesDbContract.TEXT),
                intent.getLongExtra(Contract.NotesDbContract.DATE,0),
                intent.getIntExtra(Contract.NotesDbContract.NOTE_COLOR,R.style.NewTheme_blue_theme));
        mEditText = (AppCompatEditText) findViewById(R.id.edit_text_note);
        mEditText.setText(mCacheNote.getmNoteText());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                mCacheNote.setmNoteText(mEditText.getText().toString());
                mCacheNote.setmDate(System.currentTimeMillis());
                ((NotesApp)getApplication()).getmNotesStorage().updateNote(
                        mCacheNote
                );
                finish();
                return true;
            case R.id.remove_note:
                ((NotesApp)getApplication()).getmNotesStorage().deleteNote(
                        mCacheNote
                );
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
