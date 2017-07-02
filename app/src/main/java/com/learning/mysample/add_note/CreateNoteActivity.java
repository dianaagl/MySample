package com.learning.mysample.add_note;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import com.learning.mysample.Note;
import com.learning.mysample.NotesApp;
import com.learning.mysample.R;

/**
 * Created by Диана on 01.07.2017.
 */
public class CreateNoteActivity extends AppCompatActivity {
    private AppCompatEditText mNoteTextEditText;
    private int mTheme;

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
                                mTheme));
                finish();
                return true;
            case R.id.remove_note:
                finish();
                return true;
            case R.id.choose_color:
                HorizontalScrollView llBottomSheet = (HorizontalScrollView) findViewById(R.id.bottom_sheet);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                bottomSheetBehavior.setPeekHeight(150);
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

        mTheme = R.style.white_theme;

        findViewById(R.id.blue_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTheme = R.style.blue_theme;
                setTheme(R.style.blue_theme);
            }
        });
        findViewById(R.id.orange_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme(R.style.orange_theme);
                mTheme = R.style.orange_theme;
            }
        });
        findViewById(R.id.green_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme(R.style.green_theme);
                mTheme = R.style.green_theme;
            }
        });
        findViewById(R.id.lime_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme(R.style.lime_theme);
                mTheme = R.style.lime_theme;
            }
        });
        findViewById(R.id.pink_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme(R.style.pink_theme);
                mTheme = R.style.pink_theme;
            }
        });


    }
}
