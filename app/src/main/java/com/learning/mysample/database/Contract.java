package com.learning.mysample.database;

import android.provider.BaseColumns;

/**
 * Created by Диана on 30.06.2017.
 */
public class Contract {
    public static abstract class NotesDbContract implements BaseColumns {
        public static final String TABLE_NAME = "notes";

        public static final String DATE = "date";
        public static final String TEXT = "note_text";
        public static final String NOTE_COLOR = "note_color";

    }
}
