package com.example.ymnd.widgetsample

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.RemoteViews
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            requestWidget()
        }
    }

    /**
     *
     */
    private fun requestWidget() {
        val widgetManager = AppWidgetManager.getInstance(this)
        val provider = ComponentName(this, NewAppWidget::class.java)
        /**
         * DefaultLauncherでWidgetリクエストが使用できるかをチェックする
         * Android O(API 26)から追加された
         * requestPinAppWidgetの方でも対応しているかを確認できるが
         * これを使うことで対応しているかを実行前に確認できる
         */
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O && widgetManager.isRequestPinAppWidgetSupported) {
            /**
             * callbackで呼びたいintentをここで用意する
             * bundleにセットしてあげれば情報をとばせる
             *
             * 成功時にAppWidgetManager.EXTRA_APPWIDGET_IDがintentに詰め込まれているため
             * widgetの画面で出し分けたい項目があれば、（複数カスタムwidget対応）
             * widget_idと紐付けて保存してあげれば良い
             *
             * configに関してはprovider:configureから呼び出してあげる
             *
             * Broadcastにしてもいいし、Activityにもどしても良い
             *
             * request -> widgetProvider or 保存用BroadcastReceiver -> Activity のコンボでも良さそう
             *
             * ref: https://github.com/sigute/WidgetsDemo
             */
            val callbackIntent = Intent(this, ColorConfigureActivity::class.java)

            /**
             * Widgetをおいたあとにどうしたいかをcallbackとしてセットできる
             * Widgetの追加ダイアログは２パターン存在
             * 自動で追加or手動で追加
             * 自動的に追加の場合はアプリの画面から遷移しないのでよいが、手動だとホームランチャーに遷移
             * 自分のアプリに戻ってこないので、Broadcastで通知するなりActivityを再度開くなりで登録を行う
             *
             * 追加時にはonReceiveがwidget_idが付いた状態で呼ばれるので更新はそっちで行う
             */
            val successCallback = PendingIntent.getActivity(this, 0, callbackIntent, 0)

            /**
             * custom previewをセットする
             * preview imageと異なり、remote viewsをカスタムできる
             * あくまでもpreview用である。このあと、updateされるから注意して。
             */
            val extras = Bundle()
            val remoteViews = RemoteViews(this.packageName, R.layout.new_app_widget)
            remoteViews.setTextViewText(R.id.button, "てすと")
            extras.putParcelable(AppWidgetManager.EXTRA_APPWIDGET_PREVIEW, remoteViews)
            /**
             * requestPinAppWidget
             * 返り値はbooleanであるtrueの場合、requestPinAppWidgetに対応している意味
             * これはWidgetが追加できたことを意味しない
             *
             * extras:Bundle
             * https://developer.android.com/reference/android/appwidget/AppWidgetManager.html#EXTRA_APPWIDGET_PREVIEW
             * custom previewを使用したい場合に使用する：remoteViewをここで生成してbundleに詰め込む
             * https://medium.com/wearebase/android-oreo-widget-pinning-in-kotlin-398d529eab28
             *
             * successCallbackはnullでも良い。ただその場合、追加時に何も起きない。
             *
             * 現在使用しているランチャーにWidgetを追加するリクエストダイアログを呼び出す
             * ForegroundなActivity／Serviceから呼ばないと例外が出て落ちます。
             */
            widgetManager.requestPinAppWidget(provider, extras, successCallback)
        } else {
            Toast.makeText(this, "AndroidOでないので未対応です", Toast.LENGTH_SHORT).show()
        }
    }
}
