package com.bqliang.nfushortcuts.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.databinding.ActivityMainBinding
import com.bqliang.nfushortcuts.fragment.MyBottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private var myBottomSheetDialogFragment : BottomSheetDialogFragment? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        thread {
            myBottomSheetDialogFragment = MyBottomSheetDialogFragment()
        }
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
          super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        myBottomSheetDialogFragment?.show(supportFragmentManager, "MyBottomSheetDialog")
        super.onStart()
    }
}