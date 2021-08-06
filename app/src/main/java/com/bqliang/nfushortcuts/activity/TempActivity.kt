package com.bqliang.nfushortcuts.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.service.MyService
import com.bqliang.nfushortcuts.tools.showToast

class TempActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MyService::class.java)
        startForegroundService(intent)
        this.finish()
    }
}