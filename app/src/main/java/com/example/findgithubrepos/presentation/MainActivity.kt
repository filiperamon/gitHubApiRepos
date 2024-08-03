package com.example.findgithubrepos.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.findgithubrepos.R
import com.example.findrepositorygithub.MyApplication


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as MyApplication).getNetComponent().inject(this)
    }
}