package com.app.oswaldo.bloggers.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.oswaldo.bloggers.R
import com.app.oswaldo.bloggers.utils.makeStatusBarTransparent

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        setContentView(R.layout.activity_detail)
    }

    fun backAction(view: View) {
        finish()
    }
}