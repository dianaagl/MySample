package com.learning.mysample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

/**
 * Created by Диана on 01.07.2017.
 */
public class CreateNoteActivity extends AppCompatActivity {
    AppCompatEditText mNoteTextEditText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.note_layout);

        super.onCreate(savedInstanceState);
        mNoteTextEditText = (AppCompatEditText) findViewById(R.id.edit_text_note);
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NotesApp)getApplication()).getmNotesStorage().insertNote(
                        new Note(-1,
                        String.valueOf(mNoteTextEditText.getText()),System.currentTimeMillis(),
                                "red"));
            }
        });
    }
}
