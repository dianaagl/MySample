package com.learning.mysample;

/**
 * Created by Диана on 30.06.2017.
 */
public class Note {
    private long mId;
    private String mNoteText;
    private double mDate;
    private String mNoteColor;

    public Note(long mId, String mNoteText, double mDate, String mNoteColor) {
        this.mId = mId;
        this.mNoteText = mNoteText;
        this.mDate = mDate;
        this.mNoteColor = mNoteColor;
    }


    public long getmId() {
        return mId;
    }

    public String getmNoteText() {
        return mNoteText;
    }

    public double getmDate() {
        return mDate;
    }

    public String getmNoteColor() {
        return mNoteColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        if (mId != note.mId) return false;
        if (Double.compare(note.mDate, mDate) != 0) return false;
        if (!mNoteText.equals(note.mNoteText)) return false;
        return mNoteColor.equals(note.mNoteColor);
    }
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mNoteText != null ? mNoteText.hashCode() : 0);
        temp = Double.doubleToLongBits(mDate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (mNoteColor != null ? mNoteColor.hashCode() : 0);
        return result;
    }


}
