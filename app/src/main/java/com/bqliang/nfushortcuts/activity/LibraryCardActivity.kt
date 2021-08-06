package com.bqliang.nfushortcuts.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bqliang.nfushortcuts.R
import com.google.zxing.BarcodeFormat

import com.king.zxing.util.CodeUtils

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bqliang.nfushortcuts.dialog.LibraryCardSettingAlertDialog
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.appbar.MaterialToolbar
import kotlin.concurrent.thread


class LibraryCardActivity : AppCompatActivity() {

    private lateinit var barCode: ImageView
    private lateinit var text: TextView
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        thread {
            id = getSharedPreferences("app_data", MODE_PRIVATE)
                .getString("id", null)
            if (id.isNullOrBlank()){
                getText(R.string.tooltip_configure_id).toString().showToast(Toast.LENGTH_LONG)
            }
        }

        setContentView(R.layout.activity_library_card)
        barCode = findViewById(R.id.barCode)
        text = findViewById(R.id.text)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.library_card)
        id?.let { createBarCode(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.library_card_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.id_setting -> LibraryCardSettingAlertDialog(this)
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    fun createBarCode(id: String) {
        thread {
            val bitmap = CodeUtils.createBarCode(id, BarcodeFormat.CODE_128, 800, 200)
            runOnUiThread {
                barCode.setImageBitmap(bitmap)
                text.text = id
            }
        }
    }

}