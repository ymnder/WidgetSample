package com.example.ymnd.widgetsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService


/**
 * Created by ymnd on 2018/02/05.
 */
class ListWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        Log.v("[Widget]", "list onGetViewFactory")
        return ListViewFactory.newInstance(applicationContext)
    }

    /**
     * ListItemを生成・更新するクラス
     */
    class ListViewFactory(val context: Context) : RemoteViewsService.RemoteViewsFactory {
        companion object {
            fun newInstance(context: Context): ListViewFactory {
                return ListViewFactory(context)
            }
        }

        private val items = arrayListOf<String>()

        override fun onCreate() {
            Log.v("[Widget]", "list onCreate")
            for (i in 1..10) {
                items.add("item $i")
            }
        }

        override fun getLoadingView(): RemoteViews? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun onDataSetChanged() {
            Log.v("[Widget]", "list onDataSetChanged")
            //データが変更された場合はここで更新を行う
        }

        override fun hasStableIds(): Boolean {
            return true
        }

        override fun getViewAt(position: Int): RemoteViews {
            Log.v("[Widget]", "list getViewAt $position")
            val rv = RemoteViews(context.packageName, R.layout.list_item)
            rv.setTextViewText(R.id.list_text, items[position])
            //ここでIntentを詰める。このintentはこのクラスの呼び出し元へ渡される
            rv.setOnClickFillInIntent(R.id.list_container, createIntent(position))
            return rv
        }

        private fun createIntent(position: Int): Intent {
            val extras = Bundle()
            extras.putInt(ListAppWidget.EXTRA_WIDGET_ITEM, position)
            val fillInIntent = Intent()
            fillInIntent.putExtras(extras)
            return fillInIntent
        }

        override fun getCount(): Int {
            return items.size
        }

        override fun getViewTypeCount(): Int {
            return 1
        }

        override fun onDestroy() {
            Log.v("[Widget]", "list onDestroy")
        }

    }
}