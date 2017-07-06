package com.learning.mysample;

import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.RemoteViews;
import com.learning.mysample.database.Contract;
import com.learning.mysample.database.NotesStorage;
import com.learning.mysample.edit_note.EditNoteActivity;
import com.learning.mysample.notes.NotesActivity;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Диана on 30.06.2017.
 */
public class NotesWidget extends AppWidgetProvider  {
    public NotesWidget() {

    }

    private Context mContext;
    private static final String TAG = "NotesWidget";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        mContext = context;
        //Log.e(TAG,"onUpdate");
        Intent intent = new Intent(context,MyService.class);
        context.startService(intent);

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        context.stopService(new Intent(context,MyService.class));

    }




    public static class MyService extends IntentService {
        private static final String TAG = "Service";
        public static final String PATTERN = "MM/dd/yyyy";
        public static final int REQUEST_CODE = 2;

        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         *
         * @param name Used to name the worker thread, important only for debugging.
         */
        public MyService() {
            super("MyIntentServ");
        }



        @Override
        protected void onHandleIntent(Intent intent) {
            Note lastNote = NotesApp.getmNotesStorage().getLastNote();



            RemoteViews updateViews = new RemoteViews(this.getPackageName(),
                    R.layout.notes_widget);
            if(lastNote != null) {
                updateViews.setTextViewText(R.id.note_text, lastNote.getmNoteText());
                updateViews.setTextViewText(R.id.note_date, new SimpleDateFormat(PATTERN).format(lastNote.getmDate()));

                updateViews.setInt(R.id.note_text,
                        "setBackgroundColor",ContextCompat.getColor(this,lastNote.getmNoteTheme()));

                //Log.e(TAG, "data = " + new SimpleDateFormat(PATTERN).format(lastNote.getmDate()));
            }
            else {
                updateViews.setTextViewText(R.id.note_text,getResources().getString(
                        R.string.notes_app_name));
                updateViews.setTextViewText(R.id.note_date,
                        new SimpleDateFormat(PATTERN).format(System.currentTimeMillis()));

               }


            Intent intentActivity = new Intent(this, EditNoteActivity.class);
            intentActivity.putExtra(Contract.NotesDbContract.TEXT,lastNote.getmNoteText());
            intentActivity.putExtra(Contract.NotesDbContract.DATE,lastNote.getmDate());
            intentActivity.putExtra(Contract.NotesDbContract.NOTE_COLOR,lastNote.getmNoteTheme());
            intentActivity.putExtra(Contract.NotesDbContract._ID, lastNote.getmId());

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this,
                    REQUEST_CODE,
                    intentActivity,
                    PendingIntent.FLAG_CANCEL_CURRENT);
            //Log.e(TAG, lastNote.toString());

            ComponentName thisWidget = new ComponentName(this, NotesWidget.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(this);
            int[] appWidgetIds = manager.getAppWidgetIds(thisWidget);

            for(int id:appWidgetIds) {
                updateViews.setOnClickPendingIntent(R.id.widget, pendingIntent);
            }


            try {
                manager.updateAppWidget(appWidgetIds, updateViews);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }



    }
}
