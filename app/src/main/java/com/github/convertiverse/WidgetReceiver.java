package com.github.convertiverse;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.icu.number.Precision;
import android.widget.RemoteViews;

import com.github.convertiverse.category.ConverterCategory;
import com.github.convertiverse.unit.Unit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class WidgetReceiver extends AppWidgetProvider {

    private static String value1 = "0";
    private static String value2 = "0";
    private static ConverterCategory category = ConvertiverseApp.getInstance().getCategory("currency");
    private static Unit unit1 = ConvertiverseApp.getInstance().getUnit("dollar");
    private static Unit unit2 = ConvertiverseApp.getInstance().getUnit("euro");
    private static List<ConverterCategory> categoriesList = ConvertiverseApp.getInstance().getCategories();
    private static List<? extends Unit> unitList = ConvertiverseApp.getInstance().getUnits("currency");
    private static int currentValueSelected = 1;
    private static int currentUnitSelected = 1;
    private static int categoryPage = 1;
    private static int unit1Page = 1;
    private static int unit2Page = 1;

    private static final String ACTION_WIDGET_CATEGORY = "ACTION_WIDGET_CATEGORY";
    private static final String ACTION_WIDGET_UNIT_1 = "ACTION_WIDGET_UNIT_1";
    private static final String ACTION_WIDGET_UNIT_2 = "ACTION_WIDGET_UNIT_2";
    private static final String ACTION_WIDGET_VALUE_1 = "ACTION_WIDGET_VALUE_1";
    private static final String ACTION_WIDGET_VALUE_2 = "ACTION_WIDGET_VALUE_2";
    private static final String ACTION_WIDGET_BTN_VALUE_0 = "ACTION_WIDGET_BTN_VALUE_0";
    private static final String ACTION_WIDGET_BTN_VALUE_1 = "ACTION_WIDGET_BTN_VALUE_1";
    private static final String ACTION_WIDGET_BTN_VALUE_2 = "ACTION_WIDGET_BTN_VALUE_2";
    private static final String ACTION_WIDGET_BTN_VALUE_3 = "ACTION_WIDGET_BTN_VALUE_3";
    private static final String ACTION_WIDGET_BTN_VALUE_4 = "ACTION_WIDGET_BTN_VALUE_4";
    private static final String ACTION_WIDGET_BTN_VALUE_5 = "ACTION_WIDGET_BTN_VALUE_5";
    private static final String ACTION_WIDGET_BTN_VALUE_6 = "ACTION_WIDGET_BTN_VALUE_6";
    private static final String ACTION_WIDGET_BTN_VALUE_7 = "ACTION_WIDGET_BTN_VALUE_7";
    private static final String ACTION_WIDGET_BTN_VALUE_8 = "ACTION_WIDGET_BTN_VALUE_8";
    private static final String ACTION_WIDGET_BTN_VALUE_9 = "ACTION_WIDGET_BTN_VALUE_9";
    private static final String ACTION_WIDGET_BTN_VALUE_DEL = "ACTION_WIDGET_BTN_VALUE_DEL";
    private static final String ACTION_WIDGET_BTN_VALUE_POINT = "ACTION_WIDGET_BTN_VALUE_POINT";
    private static final String ACTION_WIDGET_BTN_UNIT_1 = "ACTION_WIDGET_BTN_UNIT_1";
    private static final String ACTION_WIDGET_BTN_UNIT_2 = "ACTION_WIDGET_BTN_UNIT_2";
    private static final String ACTION_WIDGET_BTN_UNIT_3 = "ACTION_WIDGET_BTN_UNIT_3";
    private static final String ACTION_WIDGET_BTN_UNIT_4 = "ACTION_WIDGET_BTN_UNIT_4";
    private static final String ACTION_WIDGET_BTN_UNIT_5 = "ACTION_WIDGET_BTN_UNIT_5";
    private static final String ACTION_WIDGET_BTN_UNIT_6 = "ACTION_WIDGET_BTN_UNIT_6";
    private static final String ACTION_WIDGET_BTN_UNIT_BACKWARD = "ACTION_WIDGET_BTN_UNIT_BACKWARD";
    private static final String ACTION_WIDGET_BTN_UNIT_FORWARD = "ACTION_WIDGET_BTN_UNIT_FORWARD";
    private static final String ACTION_WIDGET_BTN_CATEGORY_1 = "ACTION_WIDGET_BTN_CATEGORY_1";
    private static final String ACTION_WIDGET_BTN_CATEGORY_2 = "ACTION_WIDGET_BTN_CATEGORY_2";
    private static final String ACTION_WIDGET_BTN_CATEGORY_3 = "ACTION_WIDGET_BTN_CATEGORY_3";
    private static final String ACTION_WIDGET_BTN_CATEGORY_4 = "ACTION_WIDGET_BTN_CATEGORY_4";
    private static final String ACTION_WIDGET_BTN_CATEGORY_BACKWARD = "ACTION_WIDGET_BTN_CATEGORY_BACKWARD";
    private static final String ACTION_WIDGET_BTN_CATEGORY_FORWARD = "ACTION_WIDGET_BTN_CATEGORY_FORWARD";

    // funktioniert noch nicht richtig
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

        // Construct an Intent which is pointing this class.
        Intent intentCategory = new Intent(context, WidgetReceiver.class);
        Intent intentUnit1 = new Intent(context, WidgetReceiver.class);
        Intent intentUnit2 = new Intent(context, WidgetReceiver.class);
        Intent intentValue1 = new Intent(context, WidgetReceiver.class);
        Intent intentValue2 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnValue0 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnValue1 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnValue2 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnValue3 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnValue4 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnValue5 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnValue6 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnValue7 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnValue8 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnValue9 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnValueDel = new Intent(context, WidgetReceiver.class);
        Intent intentBtnValuePoint = new Intent(context, WidgetReceiver.class);
        Intent intentBtnUnit1 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnUnit2 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnUnit3 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnUnit4 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnUnit5 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnUnit6 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnUnitBackward = new Intent(context, WidgetReceiver.class);
        Intent intentBtnUnitForward = new Intent(context, WidgetReceiver.class);
        Intent intentBtnCategory1 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnCategory2 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnCategory3 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnCategory4 = new Intent(context, WidgetReceiver.class);
        Intent intentBtnCategoryBackward = new Intent(context, WidgetReceiver.class);
        Intent intentBtnCategoryForward = new Intent(context, WidgetReceiver.class);

        intentCategory.setAction(ACTION_WIDGET_CATEGORY);
        intentUnit1.setAction(ACTION_WIDGET_UNIT_1);
        intentUnit2.setAction(ACTION_WIDGET_UNIT_2);
        intentValue1.setAction(ACTION_WIDGET_VALUE_1);
        intentValue2.setAction(ACTION_WIDGET_VALUE_2);
        intentBtnValue0.setAction(ACTION_WIDGET_BTN_VALUE_0);
        intentBtnValue1.setAction(ACTION_WIDGET_BTN_VALUE_1);
        intentBtnValue2.setAction(ACTION_WIDGET_BTN_VALUE_2);
        intentBtnValue3.setAction(ACTION_WIDGET_BTN_VALUE_3);
        intentBtnValue4.setAction(ACTION_WIDGET_BTN_VALUE_4);
        intentBtnValue5.setAction(ACTION_WIDGET_BTN_VALUE_5);
        intentBtnValue6.setAction(ACTION_WIDGET_BTN_VALUE_6);
        intentBtnValue7.setAction(ACTION_WIDGET_BTN_VALUE_7);
        intentBtnValue8.setAction(ACTION_WIDGET_BTN_VALUE_8);
        intentBtnValue9.setAction(ACTION_WIDGET_BTN_VALUE_9);
        intentBtnValueDel.setAction(ACTION_WIDGET_BTN_VALUE_DEL);
        intentBtnValuePoint.setAction(ACTION_WIDGET_BTN_VALUE_POINT);
        intentBtnUnit1.setAction(ACTION_WIDGET_BTN_UNIT_1);
        intentBtnUnit2.setAction(ACTION_WIDGET_BTN_UNIT_2);
        intentBtnUnit3.setAction(ACTION_WIDGET_BTN_UNIT_3);
        intentBtnUnit4.setAction(ACTION_WIDGET_BTN_UNIT_4);
        intentBtnUnit5.setAction(ACTION_WIDGET_BTN_UNIT_5);
        intentBtnUnit6.setAction(ACTION_WIDGET_BTN_UNIT_6);
        intentBtnUnitBackward.setAction(ACTION_WIDGET_BTN_UNIT_BACKWARD);
        intentBtnUnitForward.setAction(ACTION_WIDGET_BTN_UNIT_FORWARD);
        intentBtnCategory1.setAction(ACTION_WIDGET_BTN_CATEGORY_1);
        intentBtnCategory2.setAction(ACTION_WIDGET_BTN_CATEGORY_2);
        intentBtnCategory3.setAction(ACTION_WIDGET_BTN_CATEGORY_3);
        intentBtnCategory4.setAction(ACTION_WIDGET_BTN_CATEGORY_4);
        intentBtnCategoryBackward.setAction(ACTION_WIDGET_BTN_CATEGORY_BACKWARD);
        intentBtnCategoryForward.setAction(ACTION_WIDGET_BTN_CATEGORY_FORWARD);

        // And this time we are sending a broadcast with getBroadcast
        PendingIntent pendingIntentCategory = PendingIntent.getBroadcast(context, 0, intentCategory, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentUnit1 = PendingIntent.getBroadcast(context, 0, intentUnit1, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentUnit2 = PendingIntent.getBroadcast(context, 0, intentUnit2, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentValue1 = PendingIntent.getBroadcast(context, 0, intentValue1, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentValue2 = PendingIntent.getBroadcast(context, 0, intentValue2, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnValue0 = PendingIntent.getBroadcast(context, 0, intentBtnValue0, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnValue1 = PendingIntent.getBroadcast(context, 0, intentBtnValue1, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnValue2 = PendingIntent.getBroadcast(context, 0, intentBtnValue2, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnValue3 = PendingIntent.getBroadcast(context, 0, intentBtnValue3, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnValue4 = PendingIntent.getBroadcast(context, 0, intentBtnValue4, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnValue5 = PendingIntent.getBroadcast(context, 0, intentBtnValue5, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnValue6 = PendingIntent.getBroadcast(context, 0, intentBtnValue6, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnValue7 = PendingIntent.getBroadcast(context, 0, intentBtnValue7, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnValue8 = PendingIntent.getBroadcast(context, 0, intentBtnValue8, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnValue9 = PendingIntent.getBroadcast(context, 0, intentBtnValue9, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnValueDel = PendingIntent.getBroadcast(context, 0, intentBtnValueDel, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnValuePoint = PendingIntent.getBroadcast(context, 0, intentBtnValuePoint, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnUnit1 = PendingIntent.getBroadcast(context, 0, intentBtnUnit1, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnUnit2 = PendingIntent.getBroadcast(context, 0, intentBtnUnit2, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnUnit3 = PendingIntent.getBroadcast(context, 0, intentBtnUnit3, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnUnit4 = PendingIntent.getBroadcast(context, 0, intentBtnUnit4, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnUnit5 = PendingIntent.getBroadcast(context, 0, intentBtnUnit5, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnUnit6 = PendingIntent.getBroadcast(context, 0, intentBtnUnit6, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnUnitBackward = PendingIntent.getBroadcast(context, 0, intentBtnUnitBackward, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnUnitForward = PendingIntent.getBroadcast(context, 0, intentBtnUnitForward, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnCategory1 = PendingIntent.getBroadcast(context, 0, intentBtnCategory1, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnCategory2 = PendingIntent.getBroadcast(context, 0, intentBtnCategory2, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnCategory3 = PendingIntent.getBroadcast(context, 0, intentBtnCategory3, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnCategory4 = PendingIntent.getBroadcast(context, 0, intentBtnCategory4, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnCategoryBackward = PendingIntent.getBroadcast(context, 0, intentBtnCategoryBackward, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentBtnCategoryForward = PendingIntent.getBroadcast(context, 0, intentBtnCategoryForward, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.textView_category, pendingIntentCategory);
        views.setOnClickPendingIntent(R.id.textView_unit1, pendingIntentUnit1);
        views.setOnClickPendingIntent(R.id.textView_unit2, pendingIntentUnit2);
        views.setOnClickPendingIntent(R.id.textView_value1, pendingIntentValue1);
        views.setOnClickPendingIntent(R.id.textView_value2, pendingIntentValue2);
        views.setOnClickPendingIntent(R.id.value0, pendingIntentBtnValue0);
        views.setOnClickPendingIntent(R.id.value1, pendingIntentBtnValue1);
        views.setOnClickPendingIntent(R.id.value2, pendingIntentBtnValue2);
        views.setOnClickPendingIntent(R.id.value3, pendingIntentBtnValue3);
        views.setOnClickPendingIntent(R.id.value4, pendingIntentBtnValue4);
        views.setOnClickPendingIntent(R.id.value5, pendingIntentBtnValue5);
        views.setOnClickPendingIntent(R.id.value6, pendingIntentBtnValue6);
        views.setOnClickPendingIntent(R.id.value7, pendingIntentBtnValue7);
        views.setOnClickPendingIntent(R.id.value8, pendingIntentBtnValue8);
        views.setOnClickPendingIntent(R.id.value9, pendingIntentBtnValue9);
        views.setOnClickPendingIntent(R.id.value_del, pendingIntentBtnValueDel);
        views.setOnClickPendingIntent(R.id.value_point, pendingIntentBtnValuePoint);
        views.setOnClickPendingIntent(R.id.unit1, pendingIntentBtnUnit1);
        views.setOnClickPendingIntent(R.id.unit2, pendingIntentBtnUnit2);
        views.setOnClickPendingIntent(R.id.unit3, pendingIntentBtnUnit3);
        views.setOnClickPendingIntent(R.id.unit4, pendingIntentBtnUnit4);
        views.setOnClickPendingIntent(R.id.unit5, pendingIntentBtnUnit5);
        views.setOnClickPendingIntent(R.id.unit6, pendingIntentBtnUnit6);
        views.setOnClickPendingIntent(R.id.unit_backward, pendingIntentBtnUnitBackward);
        views.setOnClickPendingIntent(R.id.unit_forward, pendingIntentBtnUnitForward);
        views.setOnClickPendingIntent(R.id.category1, pendingIntentBtnCategory1);
        views.setOnClickPendingIntent(R.id.category2, pendingIntentBtnCategory2);
        views.setOnClickPendingIntent(R.id.category3, pendingIntentBtnCategory3);
        views.setOnClickPendingIntent(R.id.category4, pendingIntentBtnCategory4);
        views.setOnClickPendingIntent(R.id.category_backward, pendingIntentBtnCategoryBackward);
        views.setOnClickPendingIntent(R.id.category_forward, pendingIntentBtnCategoryForward);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (ACTION_WIDGET_CATEGORY.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views.setDisplayedChild(R.id.viewFlipper_input, 2);
            categoryPage = 1;
            fillCategories(views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_UNIT_1.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views.setDisplayedChild(R.id.viewFlipper_input, 1);
            currentUnitSelected = 1;
            unit2Page = 2;
            fillUnits(views);
            //...

            updateWidget(context, views);
        } else if (ACTION_WIDGET_UNIT_2.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views.setDisplayedChild(R.id.viewFlipper_input, 1);
            currentUnitSelected = 2;
            unit2Page = 1;
            fillUnits(views);
            //...

            updateWidget(context, views);
        } else if (ACTION_WIDGET_VALUE_1.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views.setDisplayedChild(R.id.viewFlipper_input, 0);
            currentValueSelected = 1;
            //...

            updateWidget(context, views);
        } else if (ACTION_WIDGET_VALUE_2.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views.setDisplayedChild(R.id.viewFlipper_input, 0);
            currentValueSelected = 2;
            //...

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_VALUE_0.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseValue(0, views);
            convert(views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_VALUE_1.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseValue(1, views);
            convert(views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_VALUE_2.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseValue(2, views);
            convert(views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_VALUE_3.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseValue(3, views);
            convert(views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_VALUE_4.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseValue(4, views);
            convert(views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_VALUE_5.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseValue(5, views);
            convert(views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_VALUE_6.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseValue(6, views);
            convert(views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_VALUE_7.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseValue(7, views);
            convert(views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_VALUE_8.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseValue(8, views);
            convert(views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_VALUE_9.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseValue(9, views);
            convert(views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_VALUE_DEL.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseValueDel(views);
            convert(views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_VALUE_POINT.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseValuePoint(views);
            convert(views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_UNIT_1.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseUnit(1, views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_UNIT_2.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseUnit(2, views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_UNIT_3.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseUnit(3, views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_UNIT_4.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseUnit(4, views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_UNIT_5.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseUnit(5, views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_UNIT_6.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseUnit(6, views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_UNIT_BACKWARD.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseUnitChangePage(1, views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_UNIT_FORWARD.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseUnitChangePage(2, views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_CATEGORY_1.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseCategory(1, views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_CATEGORY_2.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseCategory(2, views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_CATEGORY_3.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseCategory(3, views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_CATEGORY_4.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseCategory(4, views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_CATEGORY_BACKWARD.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseCategoryChangePage(1, views);

            updateWidget(context, views);
        } else if (ACTION_WIDGET_BTN_CATEGORY_FORWARD.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            views = chooseCategoryChangePage(2, views);

            updateWidget(context, views);
        }
    }



    private void updateWidget(Context context, RemoteViews views) {
        ComponentName appWidget = new ComponentName(context, WidgetReceiver.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidget, views);
    }

    private RemoteViews chooseValue(int btnID, RemoteViews views) {

        if (currentValueSelected == 1 && value1.length() <= 12) {

            if (value1.equals("0")) {
                value1 = Integer.toString(btnID);
                views.setTextViewText(R.id.textView_value1, value1);
            } else {
                value1 = value1 + Integer.toString(btnID);
                views.setTextViewText(R.id.textView_value1, value1);
            }

        } else if (currentValueSelected == 2 && value2.length() <= 12) {

            if (value2.equals("0")) {
                value2 = Integer.toString(btnID);
                views.setTextViewText(R.id.textView_value2, value2);
            } else {
                value2 = value2 + Integer.toString(btnID);
                views.setTextViewText(R.id.textView_value2, value2);
            }

        }

        return views;

    }

    private RemoteViews chooseValueDel(RemoteViews views) {

        if (currentValueSelected == 1) {

            if (value1.length() <= 1) {
                value1 = "0";
                views.setTextViewText(R.id.textView_value1, value1);
            } else {
                value1 = value1.substring(0, value1.length() - 1);
                views.setTextViewText(R.id.textView_value1, value1);
            }

        } else {

            if (value2.length() <= 1) {
                value2 = "0";
                views.setTextViewText(R.id.textView_value2, value2);
            } else {
                value2 = value2.substring(0, value2.length() - 1);
                views.setTextViewText(R.id.textView_value2, value2);
            }

        }

        return views;

    }

    private RemoteViews chooseValuePoint(RemoteViews views) {

        if (currentValueSelected == 1) {

            try {
                Double.parseDouble(value1 + ".0");
                value1 = value1 + ",";
                views.setTextViewText(R.id.textView_value1, value1);
            } catch (Exception ignored) {}

        } else {

            try {
                Double.parseDouble(value2 + ".0");
                value2 = value2 + ",";
                views.setTextViewText(R.id.textView_value2, value2);
            } catch (Exception ignored) {}

        }

        return views;

    }

    private RemoteViews chooseUnit(int btnID, RemoteViews views) {

        if (currentUnitSelected == 1) {

            try {

                unit1 = unitList.get((unit1Page * btnID) - 1);

            } catch (Exception ignored) {};

            views.setTextViewText(R.id.textView_unit1, unit1.getDisplayNameShort() + " ▼");

            currentValueSelected = 2;
            convert(views);
            currentValueSelected = 1;

        } else {

            try {

                unit2 = unitList.get((unit2Page * btnID) - 1);

            } catch (Exception ignored) {};

            views.setTextViewText(R.id.textView_unit2, unit2.getDisplayNameShort() + " ▼");

            currentValueSelected = 1;
            convert(views);
            currentValueSelected = 2;

        }

        return views;

    }

    private RemoteViews chooseUnitChangePage(int btnID, RemoteViews views) {

        if (currentUnitSelected == 1) {

            if (btnID == 1) {
                if (unit1Page-1 >= 1 && Math.round(((float) unitList.size()/6.0)) >= unit1Page-1) {
                    unit1Page--;
                    fillUnits(views);
                }
            } else {
                if (unit1Page+1 >= 1 && Math.round(((float) unitList.size()/6.0)) >= unit1Page+1) {
                    unit1Page++;
                    fillCategories(views);
                }
            }

        } else {

            if (btnID == 1) {
                if (unit2Page-1 >= 1 && Math.round(((float) unitList.size()/6.0)) >= unit2Page-1) {
                    unit2Page--;
                    fillCategories(views);
                }
            } else {
                if (unit2Page+1 >= 1 && Math.round(((float) unitList.size()/6.0)) >= unit2Page+1) {
                    unit2Page++;
                    fillCategories(views);
                }
            }

        }



        return views;

    }

    private void fillUnits(RemoteViews views) {

        int unitPage = 1;

        if (currentUnitSelected == 1) {
            unitPage = unit1Page;
        } else {
            unitPage = unit2Page;
        }

        if ((((unitPage - 1) * 6)) < unitList.size()) {
            views.setTextViewText(R.id.unit1, shortenStr(unitList.get(((unitPage - 1) * 6)).getDisplayNameShort()));
        } else {
            views.setTextViewText(R.id.unit1, "-");
        }

        if ((((unitPage-1)*6)+1) < unitList.size()) {
            views.setTextViewText(R.id.unit2, shortenStr(unitList.get(((unitPage-1)*6)+1).getDisplayNameShort()));
        } else {
            views.setTextViewText(R.id.unit2, "-");
        }

        if ((((unitPage-1)*6)+2) < unitList.size()) {
            views.setTextViewText(R.id.unit3, shortenStr(unitList.get(((unitPage-1)*6)+2).getDisplayNameShort()));
        } else {
            views.setTextViewText(R.id.unit3, "-");
        }

        if ((((unitPage-1)*6)+3) < unitList.size()) {
            views.setTextViewText(R.id.unit4, shortenStr(unitList.get(((unitPage-1)*6)+3).getDisplayNameShort()));
        } else {
            views.setTextViewText(R.id.unit4, "-");
        }

        if ((((unitPage-1)*6)+4) < unitList.size()) {
            views.setTextViewText(R.id.unit5, shortenStr(unitList.get(((unitPage-1)*6)+4).getDisplayNameShort()));
        } else {
            views.setTextViewText(R.id.unit5, "-");
        }

        if ((((unitPage-1)*6)+5) < unitList.size()) {
            views.setTextViewText(R.id.unit6, shortenStr(unitList.get(((unitPage-1)*6)+5).getDisplayNameShort()));
        } else {
            views.setTextViewText(R.id.unit6, "-");
        }

    }

    private RemoteViews chooseCategory(int btnID, RemoteViews views) {

        try {

            category = categoriesList.get((categoryPage * btnID) - 1);

        } catch (Exception ignored) {};

        views.setTextViewText(R.id.textView_category, category.getDisplayName() + " ▼");

        // set units

        unitList = ConvertiverseApp.getInstance().getUnits(category.getKey());
        currentValueSelected = 1;

        unit1 = unitList.get(0);
        value1 = "0";
        views.setTextViewText(R.id.textView_value1, value1);
        views.setTextViewText(R.id.textView_unit1, unit1.getDisplayNameShort() + " ▼");

        unit2 = unitList.get(1);
        views.setTextViewText(R.id.textView_unit2, unit2.getDisplayNameShort() + " ▼");
        convert(views);

        return views;
    }

    private RemoteViews chooseCategoryChangePage(int btnID, RemoteViews views) {

        if (btnID == 1) {
            if (categoryPage-1 >= 1 && Math.round(((float) categoriesList.size()/4.0)) >= categoryPage-1) {
                categoryPage--;
                fillCategories(views);
            }
        } else {
            if (categoryPage+1 >= 1 && Math.round(((float) categoriesList.size()/4.0)) >= categoryPage+1) {
                categoryPage++;
                fillCategories(views);
            }
        }

        return views;

    }

    private void fillCategories(RemoteViews views) {

        if ((((categoryPage - 1) * 6)) < categoriesList.size()) {
            views.setTextViewText(R.id.category1, shortenStr(categoriesList.get(((categoryPage - 1) * 6)).getDisplayName()));
        } else {
            views.setTextViewText(R.id.category1, "-");
        }

        if ((((categoryPage-1)*6)+1) < categoriesList.size()) {
            views.setTextViewText(R.id.category2, shortenStr(categoriesList.get(((categoryPage-1)*6)+1).getDisplayName()));
        } else {
            views.setTextViewText(R.id.category2, "-");
        }

        if ((((categoryPage-1)*6)+2) < categoriesList.size()) {
            views.setTextViewText(R.id.category3, shortenStr(categoriesList.get(((categoryPage-1)*6)+2).getDisplayName()));
        } else {
            views.setTextViewText(R.id.category3, "-");
        }

        if ((((categoryPage-1)*6)+3) < categoriesList.size()) {
            views.setTextViewText(R.id.category4, shortenStr(categoriesList.get(((categoryPage-1)*6)+3).getDisplayName()));
        } else {
            views.setTextViewText(R.id.category4, "-");
        }

    }

    public String shortenStr(String str) {

        if (str.length() >= 7) {
            str = str.substring(0, 6) + ".";
        }

        return str;
    }

    public double roundNum(double num) {

        BigDecimal bd = new BigDecimal(num).setScale(5, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    public String shortenNum(String num) {

        if (num.length() >= 12) {
            num = num.substring(0, 11);
        }

        return num;

    }

    public void convert(RemoteViews views) {
        if (currentValueSelected == 1) {

            double fromValue = Double.parseDouble(value1.replace(",", "."));
            double toValue = ConvertiverseApp.getInstance().convert(unit1.getKey(), fromValue, unit2.getKey());
            toValue = roundNum(toValue);
            String output = Double.toString(toValue).replace(".", ",");
            if (output.equals("0,0")) {
                output = "0";
            }
            output = shortenNum(output);
            value2 = output;
            views.setTextViewText(R.id.textView_value2, value2);

        } else {

            double fromValue = Double.parseDouble(value2.replace(",", "."));
            double toValue = ConvertiverseApp.getInstance().convert(unit2.getKey(), fromValue, unit1.getKey());
            toValue = roundNum(toValue);
            String output = Double.toString(toValue).replace(".", ",");
            if (output.equals("0,0")) {
                output = "0";
            }
            output = shortenNum(output);
            value1 = output;
            views.setTextViewText(R.id.textView_value1, value1);

        }
    }

}

