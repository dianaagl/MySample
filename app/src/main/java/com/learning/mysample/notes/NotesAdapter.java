package com.learning.mysample.notes;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.learning.mysample.Note;
import com.learning.mysample.R;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Диана on 01.07.2017.
 */
public class NotesAdapter extends BaseAdapter{
    public static final String TAG = "NotesAdapter";
    private List<Note> notes;

    public NotesAdapter(List<Note> notesList) {
        notes = new ArrayList<>(notesList);
        Log.e(TAG,"msg");
    }
    public void setNotes(List<Note> noteList){
        Log.e(TAG,"setNotes "+ noteList);
        notes = new ArrayList<>(noteList);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.e(TAG,"getItemId " + notes.get(position).getmId());
        return notes.get(position).getmId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.note_item, parent,false);
            view.setTag(new NoteHolder(view));
        }

        NoteHolder noteHolder = (NoteHolder) view.getTag();
        noteHolder.setNote(notes.get(position));
        Log.e(TAG,notes.get(position).toString());
        return view;
    }
    public class NoteHolder{

        public static final String PATTERN = "MM/dd/yyyy";
        private AppCompatTextView mDateTextView;
        private AppCompatTextView mTextTextView;

        public NoteHolder(View view) {

            mDateTextView = (AppCompatTextView) view.findViewById(R.id.date);
            mTextTextView = (AppCompatTextView) view.findViewById(R.id.text);
        }

        public void  setNote(Note note){
            mDateTextView.setText(new SimpleDateFormat(PATTERN).format(new Date(note.getmDate())));
            mTextTextView.setText(note.getmNoteText());
        }

    }
}
