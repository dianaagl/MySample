package com.learning.mysample.add_note;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
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
import com.learning.mysample.edit_note.EditNoteActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Диана on 01.07.2017.
 */
public class CreateNoteActivity extends AppCompatActivity {
    public static final int NO_ID = -1;
    private AppCompatEditText mNoteTextEditText;
    private NestedScrollView mLayout;
    private BottomSheetBehavior mBottomSheetBehavior;

    private final static int COLOR_INDEX = 0;
    private final static int THEME_INDEX = 1;

    private int mTheme;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.note_layout);
        super.onCreate(savedInstanceState);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        HorizontalScrollView lBottomSheet = (HorizontalScrollView) findViewById(R.id.bottom_sheet);
        mNoteTextEditText = (AppCompatEditText) findViewById(R.id.edit_text_note);
        mLayout = (NestedScrollView) findViewById(R.id.note_layout);

        mBottomSheetBehavior = BottomSheetBehavior.from(lBottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        mTheme = R.color.white;


        for (final Map.Entry<Integer,Integer> entry: NotesApp.mColorThemes.mColorMap.entrySet()) {
            findViewById(entry.getKey()).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    mTheme = entry.getValue();
                    mLayout.setBackgroundColor(ContextCompat.getColor(CreateNoteActivity.this,entry.getValue()));
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

                    if(entry.getKey() == R.id.white_theme){
                        mNoteTextEditText.setTextColor(ContextCompat.getColor(
                                CreateNoteActivity.this,R.color.black_textColor));
                    }
                    else {
                        mNoteTextEditText.setTextColor(ContextCompat.getColor(
                                CreateNoteActivity.this,
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
                ((NotesApp)getApplication()).getmNotesStorage().insertNote(
                        new Note(NO_ID,
                                String.valueOf(mNoteTextEditText.getText()),
                                System.currentTimeMillis(),
                                mTheme));
                finish();
                return true;
            case R.id.remove_note:
                finish();
                return true;
            case R.id.choose_color:
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                mBottomSheetBehavior.setPeekHeight(R.dimen.palette_pick_height);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
