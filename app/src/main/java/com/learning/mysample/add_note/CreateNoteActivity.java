package com.learning.mysample.add_note;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.learning.mysample.Note;
import com.learning.mysample.NotesApp;
import com.learning.mysample.R;

/**
 * Created by Диана on 01.07.2017.
 */
public class CreateNoteActivity extends AppCompatActivity {
    AppCompatEditText mNoteTextEditText;

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
                ((NotesApp)getApplication()).getmNotesStorage().insertNote(
                        new Note(-1,
                                String.valueOf(mNoteTextEditText.getText()),System.currentTimeMillis(),
                                "red"));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.note_layout);

        super.onCreate(savedInstanceState);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        mNoteTextEditText = (AppCompatEditText) findViewById(R.id.edit_text_note);


    }
}
