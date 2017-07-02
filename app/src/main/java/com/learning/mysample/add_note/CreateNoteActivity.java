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
import com.learning.mysample.Note;
import com.learning.mysample.NotesApp;
import com.learning.mysample.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Диана on 01.07.2017.
 */
public class CreateNoteActivity extends AppCompatActivity {
    private AppCompatEditText mNoteTextEditText;
    private int mTheme;
    private NestedScrollView mLayout;
    private BottomSheetBehavior mBottomSheetBehavior;

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


                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                mBottomSheetBehavior.setPeekHeight(100);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.note_layout);
        super.onCreate(savedInstanceState);

        HorizontalScrollView lBottomSheet = (HorizontalScrollView) findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(lBottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mNoteTextEditText = (AppCompatEditText) findViewById(R.id.edit_text_note);

        mLayout = (NestedScrollView) findViewById(R.id.note_layout);

        mTheme = R.style.NewTheme_white_theme;


        Map<Integer,Integer[]>list = new HashMap();

        list.put(R.id.purple_theme,new Integer[]{R.color.purple,R.style.NewTheme_purple_theme});
        list.put(R.id.orange_theme,new Integer[]{R.color.orange, R.style.NewTheme_orange_theme});
        list.put(R.id.blue_theme,new Integer[]{R.color.blue,R.style.NewTheme_blue_theme});
        list.put(R.id.green_theme,new Integer[]{R.color.green,R.style.NewTheme_green_theme});
        list.put(R.id.pink_theme,new Integer[]{R.color.pink,R.style.NewTheme_pink_theme});
        list.put(R.id.white_theme,new Integer[]{R.color.white,R.style.NewTheme_white_theme});

        for (final Map.Entry<Integer,Integer[]> entry: list.entrySet()) {
            findViewById(entry.getKey()).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    mTheme = entry.getValue()[1];
                    mLayout.setBackgroundColor(ContextCompat.getColor(CreateNoteActivity.this,entry.getValue()[0]));
                    mNoteTextEditText.setTextColor(ContextCompat.getColor(CreateNoteActivity.this,R.color.white));

                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            });
        }


    }
}
