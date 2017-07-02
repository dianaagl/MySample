package com.learning.mysample;

/**
 * Created by Диана on 30.06.2017.
 */
public class Note {
    private long mId;
    private String mNoteText;
    private long mDate;
    private int mNoteTheme;

    public Note(long mId, String mNoteText, long mDate, int mNoteTheme) {
        this.mId = mId;
        this.mNoteText = mNoteText;
        this.mDate = mDate;
        this.mNoteTheme = mNoteTheme;
    }

    @Override
    public String toString() {
        return "Note{" +
                "mId=" + mId +
                ", mNoteText='" + mNoteText + '\'' +
                ", mDate=" + mDate +
                ", mNoteTheme='" + mNoteTheme + '\'' +
                '}';
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public void setmNoteText(String mNoteText) {
        this.mNoteText = mNoteText;
    }

    public void setmDate(long mDate) {
        this.mDate = mDate;
    }

    public void setmNoteTheme(int mNoteTheme) {
        this.mNoteTheme = mNoteTheme;
    }

    public long getmId() {
        return mId;
    }

    public String getmNoteText() {
        return mNoteText;
    }

    public long getmDate() {
        return mDate;
    }

    public int getmNoteTheme() {
        return mNoteTheme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        if (mId != note.mId) return false;
        if (mDate != note.mDate) return false;
        if (mNoteTheme != note.mNoteTheme) return false;
        return mNoteText != null ? mNoteText.equals(note.mNoteText) : note.mNoteText == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mNoteText != null ? mNoteText.hashCode() : 0);
        result = 31 * result + (int) (mDate ^ (mDate >>> 32));
        result = 31 * result + mNoteTheme;
        return result;
    }
}
