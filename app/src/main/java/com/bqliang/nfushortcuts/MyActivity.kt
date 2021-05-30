package com.bqliang.nfushortcuts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MyService::class.java)
        startForegroundService(intent)
        this.finish()
    }
}