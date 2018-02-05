package com.example.ymnd.widgetsample

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews


/**
 * Implementation of App Widget functionality.
 */
class ListAppWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        const val EXTRA_WIDGET_ITEM = "extra_widget_item"

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {

            val intent = Intent(context, ListWidgetService::class.java)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)

            val rv = RemoteViews(context.packageName, R.layout.list_app_widget)

            rv.setRemoteAdapter(R.id.widgetListView, intent)

            rv.setEmptyView(R.id.widgetListView, android.R.id.empty)

            val openIntent = DetailActivity.createIntent(context)
            openIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)

            val pendingIntent = PendingIntent.getActivity(context, 0, openIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT)
            rv.setPendingIntentTemplate(R.id.widgetListView, pendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, rv)
        }
    }
}

