package com.learning.mysample.edit_note;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import com.learning.mysample.ColorThemes;
import com.learning.mysample.Note;
import com.learning.mysample.NotesApp;
import com.learning.mysample.R;
import com.learning.mysample.add_note.CreateNoteActivity;
import com.learning.mysample.database.Contract;
import com.learning.mysample.database.Contract.NotesDbContract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Диана on 01.07.2017.
 */

public class EditNoteActivity extends AppCompatActivity {
    private AppCompatEditText mEditText;
    private Note mCacheNote;
    private NestedScrollView mLayout;
    private BottomSheetBehavior mBottomSheetBehavior;

    private final static int COLOR_INDEX = 0;
    private final static int THEME_INDEX = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(R.string.app_name);

        mEditText = (AppCompatEditText) findViewById(R.id.edit_text_note);
        HorizontalScrollView lBottomSheet = (HorizontalScrollView) findViewById(R.id.bottom_sheet);
        mLayout = (NestedScrollView) findViewById(R.id.note_layout);

        Intent intent = getIntent();
        mCacheNote = new Note(
                intent.getLongExtra(Contract.NotesDbContract._ID,-1),
                intent.getStringExtra(Contract.NotesDbContract.TEXT),
                intent.getLongExtra(Contract.NotesDbContract.DATE,0),
                intent.getIntExtra(Contract.NotesDbContract.NOTE_COLOR,
                        R.color.white));
        if(mCacheNote.getmNoteTheme() != R.color.white){
            mEditText.setTextColor(ContextCompat.getColor(this,R.color.white));
        }

        mEditText.setText(mCacheNote.getmNoteText());

        mBottomSheetBehavior = BottomSheetBehavior.from(lBottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);


        mLayout.setBackgroundColor(
                ContextCompat.getColor(EditNoteActivity.this,
                        mCacheNote.getmNoteTheme()));


        for (final Map.Entry<Integer,Integer> entry: NotesApp.mColorThemes.mColorMap.entrySet()) {
            findViewById(entry.getKey()).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    mCacheNote.setmNoteTheme( entry.getValue());
                    mLayout.setBackgroundColor(ContextCompat.getColor(
                            EditNoteActivity.this,
                            entry.getValue()));
                    if(entry.getKey() == R.id.white_theme){
                        mEditText.setTextColor(ContextCompat.getColor(
                                EditNoteActivity.this,R.color.black_textColor));
                    }
                    else {
                        mEditText.setTextColor(ContextCompat.getColor(
                                EditNoteActivity.this,
                                R.color.white));
                    }


                }
            });
        }


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
            case R.id.choose_color:
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                mBottomSheetBehavior.setPeekHeight(R.dimen.palette_pick_height);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
