package com.android.dubizzle.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.dubizzle.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}