package com.udacity.nd.projects.mobfinder.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.udacity.nd.projects.mobfinder.MobileDetailsActivity;
import com.udacity.nd.projects.mobfinder.R;
import com.udacity.nd.projects.mobfinder.utils.PreferencesUtils;

/**
 * Implementation of App Widget functionality.
 */
public class MobFinderWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_mob_finder);

        Intent serviceIntent = new Intent(context, MobFinderWidgetService.class);

        views.setRemoteAdapter(R.id.widget_mobile_list, serviceIntent);
        views.setEmptyView(R.id.widget_mobile_list, R.layout.widget_fallback);

        Intent mobileDetailsIntent = new Intent(context, MobileDetailsActivity.class);
        PendingIntent pendingIntentTemplate = PendingIntent.getActivity(context, 0, mobileDetailsIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_mobile_list, pendingIntentTemplate);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateAppWidgets(Context context) {
        AppWidgetManager widgetManager = (AppWidgetManager) context.getSystemService(Context.APPWIDGET_SERVICE);
        if (widgetManager != null) {
            int[] appWidgetIds = widgetManager.getAppWidgetIds(new ComponentName(context, MobFinderWidget.class));
            widgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_mobile_list);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

