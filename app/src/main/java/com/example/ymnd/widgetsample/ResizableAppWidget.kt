package com.example.ymnd.widgetsample

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class ResizableAppWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
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

    override fun onAppWidgetOptionsChanged(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int, newOptions: Bundle?) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
        Log.v("[Widget]", "onAppWidgetOptionsChanged")
        Log.v("[Widget]", "min width: ${newOptions?.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH)}, min height: ${newOptions?.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT)}")
        Log.v("[Widget]", "max width: ${newOptions?.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH)}, max height: ${newOptions?.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT)}")
        val maxWidth = newOptions?.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH)
        if (context != null && appWidgetManager != null && maxWidth != null) {
            updateAppWidget(context, appWidgetManager, appWidgetId, maxWidth < 180)
        }
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int, hideIcon: Boolean = false) {

            val widgetText = context.getString(R.string.appwidget_text)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.resizable_app_widget)
            views.setTextViewText(R.id.appwidget_text, widgetText)
            views.setViewVisibility(R.id.app_icon, if (hideIcon) View.GONE else View.VISIBLE )

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

