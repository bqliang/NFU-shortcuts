package com.bqliang.nfushortcuts.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.fragment.MyBottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private var myBottomSheetDialogFragment : BottomSheetDialogFragment? = null
    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        thread {
            myBottomSheetDialogFragment = MyBottomSheetDialogFragment()
        }
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
        // 实现方式 1 : 对话框弹窗
        //PrimaryAlertDialog(this)

        // 实现方式 2 : 底部弹窗
        //MyBottomSheetDialogFragment().show(supportFragmentManager, "MyBottomSheetDialog")

    }

    override fun onStart() {
        myBottomSheetDialogFragment?.show(supportFragmentManager, "MyBottomSheetDialog")
        super.onStart()
    }
}