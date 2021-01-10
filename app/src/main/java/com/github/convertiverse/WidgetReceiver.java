package com.github.convertiverse;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class WidgetReceiver extends BroadcastReceiver {


    /*static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        remoteViews.setOnClickPendingIntent(R.id.textView_category, buildButtonPendingIntent(context));

        pushWidgetUpdate(context, remoteViews);

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }*/



    private static int clickCount = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("widget", "Receiver checks if action is there");
        if(intent.getAction().equals("com.github.intent.action.CHANGE_TEXT")){
            Log.i("widget", "Btn pressed");
            updateWidgetPictureAndButtonListener(context);
        }
    }


    private void updateWidgetPictureAndButtonListener(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        remoteViews.setTextViewText(R.id.category2, "xt, test, t");

        //REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
        remoteViews.setOnClickPendingIntent(R.id.textView_category, WidgetProvider.buildButtonPendingIntent(context));

        WidgetProvider.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
    }




}

