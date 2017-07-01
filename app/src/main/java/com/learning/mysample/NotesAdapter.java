package com.learning.mysample;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.learning.mysample.Note;
import com.learning.mysample.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Диана on 01.07.2017.
 */
public class NotesAdapter extends BaseAdapter{
    private List<Note> notes = new ArrayList<>();

    public NotesAdapter() {

    }
    public void setNotes(List<Note> noteList){
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
        return notes.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.note_item,parent,false);
            view.setTag(new NoteHolder(view));
        }

        NoteHolder noteHolder = (NoteHolder) view.getTag();
        noteHolder.setNote(notes.get(position));
        return view;
    }
    public class NoteHolder{

        private AppCompatTextView mDateTextView;
        private AppCompatTextView mTextTextView;

        public NoteHolder(View view) {

            mDateTextView = (AppCompatTextView) view.findViewById(R.id.date);
            mTextTextView = (AppCompatTextView) view.findViewById(R.id.text);
        }

        public void  setNote(Note note){
            mDateTextView.setText(String.valueOf(note.getmDate()));
            mTextTextView.setText(note.getmNoteText());
        }

    }
}
