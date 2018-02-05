package com.example.ymnd.widgetsample

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [NewAppWidgetConfigureActivity]
 */
class NewAppWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.
        for (appWidgetId in appWidgetIds) {
            NewAppWidgetConfigureActivity.deleteTitlePref(context, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onRestored(context: Context?, oldWidgetIds: IntArray?, newWidgetIds: IntArray?) {
        super.onRestored(context, oldWidgetIds, newWidgetIds)
    }

    override fun onAppWidgetOptionsChanged(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int, newOptions: Bundle?) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
        Log.v("[Widget]", "onAppWidgetOptionsChanged")
        Log.v("[Widget]", "min width: ${newOptions?.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH)}, min height: ${newOptions?.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT)}")
        Log.v("[Widget]", "max width: ${newOptions?.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH)}, max height: ${newOptions?.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT)}")

    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {

            val widgetText = NewAppWidgetConfigureActivity.loadTitlePref(context, appWidgetId)
            val widgetColor = ColorConfigureActivity.loadColorPref(context, appWidgetId)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.new_app_widget)
            views.setTextViewText(R.id.appwidget_text, widgetText)
            views.setTextColor(R.id.appwidget_text, Color.GREEN)
            views.setInt(R.id.widget_layout, "setBackgroundColor", context.resources.getColor(widgetColor))

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

