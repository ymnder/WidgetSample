package com.example.ymnd.widgetsample

import android.app.Activity
import android.app.Dialog
import android.app.DialogFragment
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log


/**
 * Created by ymnd on 2018/02/05.
 */
class ColorConfigureActivity : AppCompatActivity() {
    var mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(Activity.RESULT_CANCELED)

        setContentView(R.layout.config_dialog_layout)

        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
        }

        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

        val newFragment = ColorDialogFragment()
        newFragment.arguments = extras
        newFragment.show(fragmentManager, ColorDialogFragment::class.java.canonicalName)
    }

    fun dismiss() {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        NewAppWidget.updateAppWidget(this, appWidgetManager, mAppWidgetId)

        // Make sure we pass back the original appWidgetId
        val resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId)
        setResult(Activity.RESULT_OK, resultValue)
        finish()
    }

    internal class ColorDialogFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val mAppWidgetId = arguments.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)

            val items = arrayOf(BackGroundColor.BLACK.label, BackGroundColor.WHITE.label)
            val defaultItem = BackGroundColor.BLACK.order
            val checkedItems = arrayListOf<Int>()
            checkedItems.add(defaultItem)
            return AlertDialog.Builder(activity)
                    .setTitle("Selector")
                    .setSingleChoiceItems(items, defaultItem, DialogInterface.OnClickListener { dialog, which ->
                        checkedItems.clear()
                        checkedItems.add(which)
                    })
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                        if (!checkedItems.isEmpty()) {
                            Log.d("checkedItem:", "" + checkedItems.get(0))
                            saveColorPref(activity, mAppWidgetId, BackGroundColor.getItemColor(checkedItems.get(0)))
                            (activity as ColorConfigureActivity).dismiss()
                            dismiss()
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show()
        }

        override fun onPause() {
            super.onPause()
            dismiss()
        }

        override fun onDismiss(dialog: DialogInterface?) {
            super.onDismiss(dialog)
            (activity as ColorConfigureActivity).dismiss()
        }
    }

    enum class BackGroundColor(val order: Int, val label: String, val color: Int) {
        BLACK(0, "黒色", R.color.colorBlack),
        WHITE(1, "白色", R.color.colorWhite);

        companion object {
            @ColorRes
            fun getItemColor(order: Int): Int {
                for (value in values()) {
                    if (value.order == order) {
                        return value.color
                    }
                }
                throw RuntimeException("undefined color")
            }
        }
    }

    companion object {

        private val PREFS_NAME = "com.example.ymnd.widgetsample.NewAppWidget"
        private val PREF_PREFIX_KEY = "appwidget_color_"

        // Write the prefix to the SharedPreferences object for this widget
        internal fun saveColorPref(context: Context, appWidgetId: Int, @ColorRes color: Int) {
            val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
            prefs.putInt(PREF_PREFIX_KEY + appWidgetId, color)
            prefs.apply()
        }

        // Read the prefix from the SharedPreferences object for this widget.
        // If there is no preference saved, get the default from a resource
        @ColorRes
        internal fun loadColorPref(context: Context, appWidgetId: Int): Int {
            val prefs = context.getSharedPreferences(PREFS_NAME, 0)
            return prefs.getInt(PREF_PREFIX_KEY + appWidgetId, R.color.colorAccent)
        }

        internal fun deleteColorPref(context: Context, appWidgetId: Int) {
            val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
            prefs.remove(PREF_PREFIX_KEY + appWidgetId)
            prefs.apply()
        }
    }
}