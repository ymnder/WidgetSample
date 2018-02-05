package com.example.ymnd.widgetsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by ymnd on 2018/02/05.
 */
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, DetailActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
    }
}