package com.bqliang.nfushortcuts.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.databinding.ActivityMainBinding
import com.bqliang.nfushortcuts.fragment.MyBottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private var myBottomSheetDialogFragment : BottomSheetDialogFragment? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        thread {
            myBottomSheetDialogFragment = MyBottomSheetDialogFragment()
            AppCenter.start(
                application, "f3e1fad6-dc60-4b23-8101-b7c98999bfdb",
                Analytics::class.java, Crashes::class.java
            )
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