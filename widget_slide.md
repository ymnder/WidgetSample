# Widget開発再訪
re-introduction to Widget Development
Room 2 － 2018/02/08 14:00-14:30 @ymnd

# Sample App
https://github.com/ymnder/WidgetSample

# Today's menu
1. About Widget:ウィジェットとは？
2. Widget Design:今日どんなウィジェットがあるか
3. Let's make Widget:つくってワクワク☆
4. New API: お待ちかね新API

# About Widget

## What Widget
- Widgetはメインのアプリにバンドルして提供されるミニアプリ
- アプリの機能やコンテンツに、ホーム画面からスムーズにアクセスできる
- アプリを開かずとも、ユーザーが必要な情報に触れることができる

## Why Widget: for User
- Widgetはアプリのコア機能を使えるようにしたもの
- アプリを開かなくてもアプリにリーチしてくれる
- ホーム画面でコンテンツを目にする機会が増える

## Why Widget now?
- Widgetはアプリの外でコンテンツを触れてもらえる
- 開発に簡単に取り掛かれる
- AndroidOから新しいAPIが追加された

### What is New Api
- Widget Install Dialog
- Widgetのインストールフローが改善された

### Widget picker
- Storeインストール後に、自分で追加する必要がある
- ホーム画面をロングタップする必要がある
- キャリアのランチャーごとに微妙に導線が異なる
- 他のアプリと同じところにすべて格納されているため探す手間がかかる

### Widget picker
[日経アプリの導線紹介動画]

### Widget Install Dialog
- アプリの中から直接Widgetをはりつけることができる
- 登録フローが劇的に分かりやすくなった！！

[what's new](https://developers-jp.googleblog.com/2017/08/whats-new-for-shortcuts-and-widgets-in.html)

# Widget Design
## どんなWidgetがあるか

## Widget Category
- Information widgets
- Collection widgets
- Control widgets
- Hybrid widgets

[新ガイドライン](https://material.io/guidelines/components/widgets.html#widgets-types-of-widgets)
[旧ガイドライン](https://developer.android.com/design/patterns/widgets.html)

### Information widgets
- 時計・天気などシンプルな情報を出す
- タップしたら詳細な情報をAPPで表示する

### Information widgets
[画像]
    
### Collection widgets
- 記事などの同じタイプの複数のアイテムをまとめる
- コレクションを一覧する
- タップして詳細を確認する

### Collection widgets
[画像]

### Control widgets
- アプリをコントロールする
- 再生プレーヤーのボタンや、機能を集約したランチャーのようなもの

### Control widgets
[画像]

### Hybrid widgets
- 上の要素を組み合わせたもの
- 例えば、音楽プレーヤーのランチャー＋ジャケット表示

### Hybrid widgets
[画像]

## Widget Design: News Widget Case

## News Widget Case
ニュース系ウィジェットの事例を見てみよう

### Layout
1. 一行型：一行でニュースをコンパクトに表示、次々にニュースを流す
2. リスト型：記事をリストで表示する、GridよりListが多い
3. ウォールペーパー型：写真を見せたい場合に壁紙のように表示する

アプリの機能(launcher)を使ってもらうというより、ニュースや写真を見せるタイプのWidgetが多い

### Layout: 一行型
[画像]

### Layout: リスト型
[画像]

### Layout: ウォールペーパー型
[画像]

### Configuration
- 色：白地・黒地 (night view)
- 更新頻度 (30min, 1h, more)
- ニュースの内容 (top, ranking...)

### Widgetの構成要素
- 企業ロゴ
- 設定ボタン
- 更新ボタン
- 最終更新日時
- 記事本体 -> tapでアプリを開く

# Let's make Widget
## Widget Classes
AppWidgetService: WidgetのサービスWidgetIdの発行やランチャーへのリクエストを行う
AppWidgetHost: ホーム画面のようなWidgetをUIに埋め込みたいAppWidgetサービスとのやりとりを提供する
AppWidgetHostView: AppWidgetビューを表示するための接着剤を提供する
`---ここから上は直接触らない---`
AppWidgetManager: Widgetの状態を更新したり、Widgetの情報を取得する管理クラス
AppWidgetProvider: providerを実装するのに使う便利なクラス、BroadcastReceiverをwrapしている
AppWidgetProviderInfo: providerのメタデータ。appwidget-providerのxmlタグのフィールドに対応している

## Widget Classes
[図示化]

## Widget Classes: Simplify
- AppWidgetProviderInfo：Widgetの基本情報をxmlに記述する
- AppWidgetProvider：Widgetの更新をハンドルする
- xml/layout：Widgetのレイアウト、通常のレイアウトと基本的に同じ

## How to make it
1:manifestにreceiverを追加
2:AppWidgetProviderInfoを設定
3:AppWidgetProviderの中でRemoteViewを生成＆Update

## Help me AndroidStudio!!
AndroidStudioのNewのここからウィザードを出せます！！
Widgetだからリストの一番下というなかなか分かりにくい位置です。
[スクショ]

## debug
- アプリの外の画面なので、LayoutInspectorが使えない
- AndroidStudioのbreakpointとpreviewやっていきです

## RemoteViews
- 基本的に通常のアプリと同様に組むことができます
- @RemoteViewアノテーションがついているViewが使える
- @RemotableViewMethodがあるメソッドが使える

[@RemoteViews.RemoteViewを使ったとしてもCustomViewをつくることはできないそう](http://d.hatena.ne.jp/westzaki/20120303/1330776734)


## Widget: Layout
- FrameLayout
- LinearLayout
- RelativeLayout
- GridLayout
- ※ConstraintLayoutは使えない！！

## Widget: 基本要素
- Button
- ImageButton
- ImageView
- ProgressBar
- TextView
- ViewFlipper

## Widget: タイマー系
- AnalogClock -> deprecated
- Chronometer -> タイマーをつくる
- TextClock -> デジタル時計を表示させる

## Widget: Collection
- ListView
- GridView
- StackView
- AdapterViewFlipper

## RemoteViews
- RemoteViews
- RemoteViewsFactory

### RemoteViews
- ビューヒエラルキーを持つクラス
- Widgetの表示の要

### RemoteViews: Lifecycle
- WidgetはServiceにより実装され、Serviceのライフサイクルに従う

### RemoteViewsFactory (RemoteViewsService)
- RemoteViewsFactoryを使用することでListViewのItemを作成することができる
- Adapter的な存在

### RemoteViewsFactory: Lifecycle
onCreate
↓
onDataSetChanged
getViewAt
↓
onDestroy

[図示する]

## AppWidgetProvider
- BroadcastReceiverをwrapした便利なクラス
- onReceivedで、Widget作成／更新／削除などでくるイベントをハンドリング
- onReceivedをもとに自力で実装も可能です

### 実装
```
class SampleAppWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            val views = RemoteViews(context.packageName, R.layout.widget_container)
            views.setTextViewText(R.id.textWidget, "Hello:)")
            views.setOnClickPendingIntent(R.id.button, pendingIntent)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
```

### 解説
```
//RemoteViewsに使用したいレイアウトを渡します
val views = RemoteViews(context.packageName, R.layout.widget_container)
//Textを動的に書き換える
views.setTextViewText(R.id.textWidget, "Hello:)")
//Click時の動作をセットしたいとき
views.setOnClickPendingIntent(R.id.button, pendingIntent)
```

### 解説: setTextViewText
```
views.setTextViewText(R.id.textWidget, "Hello") は
setCharSequence(viewId, "setText", text) をwrapしたものなので

views.setInt(viewId, "setBackgroundColor", color)
のようにすれば、RemoteViewに生えてないメソッドも呼べる
```

### 実装：ListViewのケース
```
fun updateWidget(context: Context, appWidgetId: Int) {
    val manager = AppWidgetManager.getInstance(context)
    val remoteViews = RemoteViews(context.getPackageName(), R.layout.widget_container)

    // ListItemに更新通知を行うためのintentを作成
    val intent = Intent(context, SampleWidgetService::class.java)
            .putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
    remoteViews.setRemoteAdapter(R.id.widgetListView, intent)

    // ListItemが空だった場合のEmpty表示
    remoteViews.setEmptyView(R.id.widgetListView, android.R.id.empty)

    // ListItemをタップしたときのintentをセット
    // ListItemの側でセットするのでなく、ここでセットする
    val onClickIntent = Intent(context, DetailActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    val onClickPendingIntent = PendingIntent.getActivity(context, 0,
            onClickIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    remoteViews.setPendingIntentTemplate(R.id.widgetListView, onClickPendingIntent)

    manager.updateAppWidget(appWidgetId, remoteViews)
}
```

### 解説:ListView
```
// ListItemに更新通知を行うためのintentを作成
// 必要があれば情報を付加する
val intent = Intent(context, SampleWidgetService::class.java)
        .putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)

// 作成したintentはListViewを対象にセット
remoteViews.setRemoteAdapter(R.id.widgetListView, intent)

// ListItemが空だった場合のEmptyViewを指定する
remoteViews.setEmptyView(R.id.widgetListView, android.R.id.empty)
```

### 解説:ListView
```
// ListItemをタップしたときのintentをセット
// ListItemに紐づくListItem側でセットする
val onClickIntent = Intent(context, DetailActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
val onClickPendingIntent = PendingIntent.getActivity(context, 0,
        onClickIntent, PendingIntent.FLAG_UPDATE_CURRENT)
remoteViews.setPendingIntentTemplate(R.id.widgetListView, onClickPendingIntent)
```

### 実装：ListView(Item)のケース
```
override fun getViewAt(position: Int): RemoteViews {
    Log.v("[Widget]", "list getViewAt $position")
    val rv = RemoteViews(context.packageName, R.layout.list_item)
    rv.setTextViewText(R.id.list_text, items[position])
    //ここでIntentを詰める。このintentはこのクラスの呼び出し元へ渡される
    //ListItemの持つデータが欲しい場合はこのintentに詰める
    rv.setOnClickFillInIntent(R.id.list_container, createIntent(position))
    return rv
}
```

## AppWidgetProviderのライフサイクル
onEnabled
onUpdated
onDeleted
onDisabled
`--- その他 ---`
onAppWidgetOptionsChanged
onRestored

### onEnabled
- Widgetがはじめてつくられたときに呼ばれる
- ２個置いた場合でも１度きりしか呼ばれない

### onUpdated：important
- Widgetの表示を更新する
- onEnabledの直後に呼ばれる
- ２個目からはこれだけしか呼ばれない

### onDeleted
Widgetが消されたときに呼ばれる

### onDisabled
最後のWidgetが消されたときに呼ばれる

### onAppWidgetOptionsChanged
- リサイズされたときに呼ばれる
- 画面サイズをdpで取得できるよ！
- [API16 above](http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/core/java/android/appwidget/AppWidgetHostView.java#updateAppWidgetSize
)

### onAppWidgetOptionsChanged
- OPTION_APPWIDGET_MIN_WIDTH：lower bound on the current width
- OPTION_APPWIDGET_MAX_WIDTH：upper bound on the current width
- OPTION_APPWIDGET_MIN_HEIGHT：lower bound on the current height
- OPTION_APPWIDGET_MAX_HEIGHT：upper bound on the current height

### onRestored
- added in API level 21
- 直後にonUpdateを呼び出す
- backupからAppWidget providerのインスタンスがリストアされたときに呼ばれる
- widgetに紐付いた永続化データを保持したい場合はここで古いidから新しいidへの引き継ぎを行う
- widgetIdとは？ー＞Appendixへ！

### onRecieve
- BroadcastReceiverのonRecieveのこと
- サブクラスではこれをoverrideするときに、各Intentのactionを見て振り分けを行っている
- 振り分けた結果、上のライフサイクルが呼ばれる
- 基本使わなくても済むが、自分でActionを管理したい場合はここを書き換える必要がある

## onUpdatedはいつ呼ばれるか
- onEnabledの直後
- onRestoredの直後
- AppWidgetManager.ACTION_APPWIDGET_UPDATEが呼ばれたとき

### ACTION_APPWIDGET_UPDATEはいつ呼ばれるか
- ウィジェット設置時
- updatePeriodMillisの時間が来たとき
- systemの起動時

### updatePeriodMills
- Widgetのアップデート間隔、ProviderInfoで指定する
- 最低更新間隔は[30分](http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/appwidget/java/com/android/server/appwidget/AppWidgetServiceImpl.java#MIN_UPDATE_PERIOD)である（保証はされない）
- ユーザーが更新間隔を調整できると良い
- [送信判定](http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/appwidget/java/com/android/server/appwidget/AppWidgetServiceImpl.java#registerForBroadcastsLocked)はここでしている
    - 0以下を指定すると時間更新でbroadcastを投げないようになる
    - 30min未満の場合は、30minにされる
- これより短く更新を行いたい場合は、AlarmManagerなどを使って自分で実装する必要がある
    - この際、updatePeriodMillsは０を指定しておくこと

## AppWidgetProviderInfo
Widgetそのものの設定をxmlで記述する

### 実装
```
<appwidget-provider xmlns:android="http://schemas.android.com/apk/res/android"
    android:minHeight="40dp"
    android:minResizeWidth="250dp"
    android:minWidth="250dp"
    android:resizeMode="horizontal|vertical"
    android:updatePeriodMillis="1800000"
    android:configure="com.example.ymnd.widgetsampleapp.ConfigureActivity"
    android:widgetCategory="home_screen|keyguard">
</appwidget-provider>
```

### AppWidgetProviderInfoの属性

### 画面の大きさを指定するもの
- int minWidth / int minHeight:デフォルトの幅と高さをdpで指定する
- int minResizeWidth / int minResizeHeight: リサイズの下限をdpで指定する(minResizeHeight <= minHeightにすること)
- int resizeMode(horizontal|vertical or none): どの方向のリサイズを許可するか

### プレビューにかかわるもの
- int previewImage: Widget選択時／リクエストダイアログのプレビューに用いられる

### レイアウト
- int initialKeyguardLayout: KeyGuardの部分に表示したときのレイアウトを指定する
- int initialLayout: ウィジェットのレイアウトを指定する
- int widgetCategory(home_screen|keyguard): ウィジェットをどこに置けるか指定する。ただし、keyguardはAndroid 5.0以上では使用できない

### 画面更新系
- int updatePeriodMillis
    ミリ秒でWidgetの更新間隔を指定する
    30minより早く更新することはできない
    更新をしたくない場合は０を指定すること

- ComponentName configure
    コンフィグ画面（Activity）をWidget設置時に呼び出すときに使用する
    ただし、onUpdateは呼ばれないので自分で呼ぶなり画面更新メソッドをたたく必要がある
    configを設定した場合、home画面にdropして追加した場合に指定したActivityを呼び出してくれる
    requestDialogから設置したときには呼ばれない

- int autoAdvanceViewId
    StackViewなど子要素を持つレイアウトのidを指定することで、ユーザーの操作がなくても自動的に進められる

# New API: requestPinAppWidget & isRequestPinAppWidgetSupported

## what is requestPinAppWidget?
- AndroidO追加されたAPI
- アプリの中からWidgetの追加を行えるようになりました！

https://android-developers.googleblog.com/2017/07/whats-new-for-shortcuts-and-widgets-in.html

## API's
- requestPinAppWidget: アプリ内にWidgetを追加します
- isRequestPinAppWidgetSupported: ホーム画面に追加できるかチェックします

## 実装
```
val widgetManager = AppWidgetManager.getInstance(this)
val provider = ComponentName(this, SampleAppWidgetProvider::class.java)
if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
if (widgetManager.isRequestPinAppWidgetSupported) {
    val callbackIntent = Intent(this, SubActivity::class.java)
    val successCallback
            = PendingIntent.getActivity(this, 0, callbackIntent, 0)
    val extras = Bundle()
    val remoteViews = RemoteViews(this.packageName, R.layout.widget_container)
    remoteViews.setTextViewText(R.id.button, "てすと")
    extras.putParcelable(EXTRA_APPWIDGET_PREVIEW, remoteViews)
    widgetManager.requestPinAppWidget(provider, extras, null)
}
```

### 解説
widgetManager.isRequestPinAppWidgetSupported
DefaultLauncherでWidgetリクエストが使用できるかをチェックする
Android O(API 26)から追加された
requestPinAppWidgetの方でも対応しているかを確認できるが
これを使うことで対応しているかを実行前に確認できる

### 解説
`val callbackIntent = Intent(this, SubActivity::class.java)`

- 成功時のcallbackで呼びたいintentをここで用意する
- 任意にbundleをセットしてもよい
- 成功時には、AppWidgetManager.EXTRA_APPWIDGET_IDがintentに詰め込まれている
- 複数カスタムwidget対応など、widgetの画面で出し分けたい項目があれば、AppWidgetIdを保存しておきましょう

### 解説
- Widgetはもともとdropしたときに実行される `provider:configure` がある
- configはrequestのメソッドでは呼ばれないので、成功時のconfig／アプリへの復帰はここでセットする
- request -> widgetProvider or 保存用BroadcastReceiver -> Activity
- という保存後にActivityを呼び戻すフローも良いね

### 解説
`val successCallback = PendingIntent.getActivity(this, 0, callbackIntent, 0)`

- Widgetをおいたあとに実行するcallbackをPendingIntentとしてセットする
- Widgetの追加ダイアログは、自動で追加or手動で追加の２パターン存在が存在する
- 自動的に追加の場合はアプリの画面から遷移しないのでよいが、手動だとホームランチャーに遷移
- 自分のアプリに戻ってこないので、Broadcastで通知するなりActivityを再度開くなりで登録を行う
- また、設定画面を引っ掛けることでユーザーにカスタムWidgetとして使ってもらうこともできる

### 解説
```
val extras = Bundle()
val remoteViews = RemoteViews(this.packageName, R.layout.widget_container)
remoteViews.setTextViewText(R.id.button, "てすと")
extras.putParcelable(EXTRA_APPWIDGET_PREVIEW, remoteViews)
```
- このbundleによりcustom previewをセットできる
- previewImageと異なり、remoteViewsをカスタムしてセット可能である
- ただ、preview用であり、onUpdateされる際に更新されてしまう

[extras:Bundle](https://developer.android.com/reference/android/appwidget/AppWidgetManager.html#EXTRA_APPWIDGET_PREVIEW)
[widget pinning](https://medium.com/wearebase/android-oreo-widget-pinning-in-kotlin-398d529eab28)
         
### 解説
`widgetManager.requestPinAppWidget(provider, extras, successCallback)`

- requestPinAppWidget: 返り値はbooleanである
- trueの場合、requestPinAppWidgetに対応している意味である
- これはWidgetが追加できたことを意味しない
- successCallbackはnullでも良い。ただその場合、追加時に何も起きない
- 現在使用しているランチャーにWidgetを追加するリクエストダイアログを呼び出す
- ForegroundなActivity／Serviceから呼ばないと例外が出て落ちる          

## requestPinAppWidgetでWidgetの配置に失敗したらどうなるか
- requestPinAppWidgetはWidgetの作成成功時にしかsuccessCallbackを呼ばない
- fallbackがないので、手動配置で失敗した場合、アプリを再び開いてもらう必要がある

## requestの注意点
- 追加ダイアログは自分でカスタムすることはできない
- Previewを変えることはできるー＞extrasにremoteViewを詰めてあげてね！！

# Conclusion
- Widgetのダイアログにより画期的に導線が分かりやすくなった
- 新規APIを含め実装が簡単なので気軽につくりはじめられる
- 多くのアプリが端末にインストールされている今だからホームの特等地をとれるWidgetを！！

# whoami
twitter:@ymnd, github:@ymnder
Application Enginner
    Android日経電子版アプリ
    Android紙面ビューアーアプリ
  
技術書典４で日経の内製の知見をまとめた本出します。

# Appendix
## How to Calculate minWidth and minHeight
WidgetのminWidth/minHeightは如何にして算出するか？
[算出方法](https://developer.android.com/guide/practices/ui_guidelines/widget_design.html#anatomy_determining_size)

`70 × cell − 30 = size`

例：1行2列のwidgetを作成する場合は
```
minHeight = 70 x 1 - 30 = 40
minWidth = 70 x 2 -30 = 110
```

※API14以前で推奨されていた計算方法がアップデートされました！

[bounding box > frame > widget controlsの図]

targetSDKがAPI14以上の場合、ホーム画面のウィジェットの各端にマージンが自動的に追加されます。
そのため、Android4.0以上ではwidgetに予め余白を追加する必要はありません。
```
@Deprecated
74 × cell - 2 = size
```

## Config Best Practice(from Material Design Guideline)
- configは簡潔にし、２〜３以上の設定項目を設けない
- コンテクストを考えるとフルスクリーンよりダイアログで選択してもらう
- セットアップ後はWidgetには設定用のボタンを表示しない

## 公式のDocumentはUpdateされてる
是非ご一読ください:))
https://developer.android.com/guide/topics/appwidgets/index.html

## how is appWidgetId created?
appWidgetIdはWidgetを更新するときに用いるID
ユニークであるはずだけど、このIDがどのように振られているかが気になる

```
intentにはこのKeyで詰められている
AppWidgetManager#EXTRA_APPWIDGET_ID
    An intent extra that contains one appWidgetId.
``` 

### AppWidgetHost.java
```
//Get a appWidgetId for a host in the calling process.
//@return a appWidgetId
public int allocateAppWidgetId() {...}

http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/core/java/android/appwidget/AppWidgetHost.java#allocateAppWidgetId
```

### AppWidgetServiceImpl.java

```
public int allocateAppWidgetId(String callingPackage, int hostId) {
...
if (mNextAppWidgetIds.indexOfKey(userId) < 0) {
    // 初期化処理
    // AppWidgetManager.INVALID_APPWIDGET_IDは0
    // Widgetは1から採番されていく。
    mNextAppWidgetIds.put(userId, AppWidgetManager.INVALID_APPWIDGET_ID + 1);
}
final int appWidgetId = incrementAndGetAppWidgetIdLocked(userId);
...
}

http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/appwidget/java/com/android/server/appwidget/AppWidgetServiceImpl.java#allocateAppWidgetId
```

### どのように番号が振られるか？
`private final SparseIntArray mNextAppWidgetIds = new SparseIntArray();`
mNextAppWidgetIdsには番号が格納されている
これは、{userId, appWidgetId}のKey,Valueで保存される
読み出した後、+1して格納するというインクリメント処理が行われている
`final int appWidgetId = peekNextAppWidgetIdLocked(userId) + 1;`
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/appwidget/java/com/android/server/appwidget/AppWidgetServiceImpl.java#incrementAndGetAppWidgetIdLocked
`return mNextAppWidgetIds.get(userId);`
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/appwidget/java/com/android/server/appwidget/AppWidgetServiceImpl.java#peekNextAppWidgetIdLocked

### どのように番号が振られるか？
AppWidgetServiceImplはServiceクラスであり、他のアプリからもアクセスされる
mNextAppWidgetIdsは他のアプリと共有されている

結論：ユニークだけど、特に何か値を生成しているわけでもない。

取得したい場合はこのメソッドを使えば取得できる
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/core/java/android/appwidget/AppWidgetManager.java#getAppWidgetIds

## requestPinAppWidget & isRequestPinAppWidgetSupportedの返り値
isRequestPinAppWidgetSupported
updateAppWidget
はそれぞれbooleanを返すが、これにはどんな違いがあるのか

上はReturn {@code TRUE} if the default launcher supports
下は{@code TRUE} if the launcher supports this feature. Note the API will return without
    waiting for the user to respond, so getting {@code TRUE} from this API does *not* mean
    the shortcut is pinned. {@code FALSE} if the launcher doesn't support this feature.
???

### isRequestPinAppWidgetSupportedの返り値

isRequestPinAppWidgetSupported
ー＞AppWidgetServiceImpl.java#isRequestPinAppWidgetSupported
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/appwidget/java/com/android/server/appwidget/AppWidgetServiceImpl.java#isRequestPinAppWidgetSupported
ー＞ShortcutService.java#isRequestPinItemSupported
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/core/java/com/android/server/pm/ShortcutService.java#isRequestPinItemSupported
ー＞ShortcutRequestPinProcessor.java#isRequestPinItemSupported
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/core/java/com/android/server/pm/ShortcutRequestPinProcessor.java#isRequestPinItemSupported
ー＞ShortcutRequestPinProcessor.java#getRequestPinConfirmationActivity
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/core/java/com/android/server/pm/ShortcutRequestPinProcessor.java#getRequestPinConfirmationActivity
ShortcutService.java#getRequestPinConfirmationActivity

### ShortcutService.java#getRequestPinConfirmationActivity 
> Find the activity that handles {@link LauncherApps#ACTION_CONFIRM_PIN_SHORTCUT} in thedefault launcher.

ここでdefault launcherから機能がハンドルされているActivityを探している
取得できなかった場合はnullを返す
これがnullであればfalseを返しており、これが非対応という判定を下している。

### requestPinAppWidgetの返り値
AppWidgetManager
ー＞AppWidgetServiceImpl
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/appwidget/java/com/android/server/appwidget/AppWidgetServiceImpl.java#requestPinAppWidget
ー＞ShortcutService#requestPinAppWidget
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/core/java/com/android/server/pm/ShortcutService.java#requestPinAppWidget
ー＞ShortcutService.java#requestPinItem
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/core/java/com/android/server/pm/ShortcutService.java#requestPinItem
ー＞ShortcutRequestPinProcessor.java#requestPinItemLocked
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/core/java/com/android/server/pm/ShortcutRequestPinProcessor.java#requestPinItemLocked
ShortcutService.java#getRequestPinConfirmationActivity

ここでdefault launcherから機能がハンドルされているActivityを探している
結果的にisRequestPinAppWidgetSupportedと同じ判定を行っている
ただ、もしtrueだった場合はそのままrequest処理が進んでいく

### 返り値の差異
- 同じものである
- リクエスト前に判定をすべきなので、isRequestPinAppWidgetSupportedを使う
- docの通り、requestPinAppWidgetは、Widgetを置けたかどうかを返さない

## requestPinAppWidgetのcallbackはどこで呼ばれるか？
- 何をもってsuccessと判定しているのだろうか。
- またfallbackとして何か呼べたりはしないだろうか。

### callbackはどこで呼ばれるか？
■AppWidgetManager
ー＞AppWidgetServiceImpl
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/appwidget/java/com/android/server/appwidget/AppWidgetServiceImpl.java#requestPinAppWidget
ー＞ShortcutService#requestPinAppWidget
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/core/java/com/android/server/pm/ShortcutService.java#requestPinAppWidget
ー＞ShortcutService.java#requestPinItem
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/core/java/com/android/server/pm/ShortcutService.java#requestPinItem

### ShortcutService.java#requestPinItem
このメソッドはShortcutの方からも呼ばれている
```
//ShortcutRequestPinProcessor.java#requestPinItemLocked
requestPinItemLocked(ShortcutInfo inShortcut,
    AppWidgetProviderInfo inAppWidget,
    Bundle extras,
    int userId,
    IntentSender resultIntent)
```

### callbackはどのタイミングで呼ばれるか？
ー＞LauncherApps.java#PinItemRequest
成功時にacceptを呼ぶ
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/core/java/android/content/pm/LauncherApps.java#PinItemRequest
ー＞ShortcutRequestPinProcessor.java#PinAppWidgetRequestInner
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/core/java/com/android/server/pm/ShortcutRequestPinProcessor.java#PinAppWidgetRequestInner
ー＞ShortcutRequestPinProcessor.java#PinItemRequestInner
ここでacceptの中身が実装されている
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/core/java/com/android/server/pm/ShortcutRequestPinProcessor.java#PinItemRequestInner
ー＞ShortcutRequestPinProcessor.java#directPinShortcut
終点：例外をキャッチしていて、作成できなかった場合はfalseを返す：tryAcceptがfalseになり、コールバックが実行されない
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/core/java/com/android/server/pm/ShortcutRequestPinProcessor.java#directPinShortcut
ー＞ShortcutService.java#fixUpIncomingShortcutInfo
http://tools.oesf.biz/android-8.0.0_r1.0/xref/frameworks/base/services/core/java/com/android/server/pm/ShortcutService.java#fixUpIncomingShortcutInfo
※Toastメッセージはこのメソッドである
http://tools.oesf.biz/android-8.0.0_r1.0/xref/packages/apps/Launcher2/src/com/android/launcher2/Launcher.java#showOutOfSpaceMessage

### callbackはどのタイミングで呼ばれるか？（終点）
```
/ /PinItemRequestInner#accept
// Pin it and send the result intent.
if (tryAccept()) {
    mProcessor.sendResultIntent(mResultIntent, extras);
    return true;
} else {
    return false;
}
```
- tryAcceptがtrueであったときのみsendResultIntentが実行される
- すなわちここでcallbackが実行される
- 失敗時は何もされない。falseが返るだけ。


