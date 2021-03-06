package com.bqliang.nfushortcuts.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.databinding.ActivityLibraryCardBinding
import com.bqliang.nfushortcuts.dialog.IDSettingAlertDialog
import com.bqliang.nfushortcuts.tools.mmkv
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.BarcodeFormat
import com.king.zxing.util.CodeUtils
import com.microsoft.appcenter.analytics.Analytics
import kotlin.concurrent.thread


class LibraryCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLibraryCardBinding

    private lateinit var barCode: ImageView
    private lateinit var idTextView: TextView
    lateinit var fab: FloatingActionButton
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLibraryCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        barCode = binding.barCode
        idTextView = binding.textId
        fab = binding.settingFab

        thread {
            id = mmkv.decodeString("id",null)
            if (id.isNullOrBlank()){
                Snackbar.make(fab, R.string.tooltip_configure_id, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.ok){ IDSettingAlertDialog(this) }
                    .show()
            }else{
                createBarCode(id!!)
            }
        }


        setSupportActionBar(binding.toolbar)

        fab.setOnClickListener { IDSettingAlertDialog(this) }
        idTextView.setOnClickListener { IDSettingAlertDialog(this) }
        Analytics.trackEvent("Open Library Card Page")
    }

    fun createBarCode(id: String) {
        thread {
            val bitmap = CodeUtils.createBarCode(id, BarcodeFormat.CODE_128, 800, 200)
            runOnUiThread {
                Glide.with(this).load(bitmap).into(barCode)
                idTextView.text = id
            }
        }
    }

}