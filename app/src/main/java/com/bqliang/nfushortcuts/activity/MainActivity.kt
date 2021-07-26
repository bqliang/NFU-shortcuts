package com.bqliang.nfushortcuts.activity

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.MyItem
import com.bqliang.nfushortcuts.service.MyService
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.adapter.AlertDialogAdapter
import com.bqliang.nfushortcuts.dialog.PrimaryAlertDialog
import com.bqliang.nfushortcuts.fragment.MyBottomSheetDialogFragment
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
        // 实现方式 1 : 对话框弹窗
        PrimaryAlertDialog(this)

        // 实现方式 2 : 底部弹窗
        //MyBottomSheetDialogFragment().show(supportFragmentManager, "MyBottomSheetDialog")

    }
}