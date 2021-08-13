package com.bqliang.nfushortcuts.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.adapter.MyRecyclerViewAdapter
import com.bqliang.nfushortcuts.dialog.CaptivePortalSettingAlertDialog
import com.bqliang.nfushortcuts.tools.getData
import com.bqliang.nfushortcuts.view.MyItemDecoration
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


class MainActivity : AppCompatActivity() {

    private lateinit var views : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCenter.start(
            application, "f3e1fad6-dc60-4b23-8101-b7c98999bfdb",
            Analytics::class.java, Crashes::class.java
        )

        views = layoutInflater.inflate(R.layout.bottom_sheet_dialog_layout, null, false)
        views.findViewById<MaterialToolbar>(R.id.toolbar).setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_about -> {
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                }
                R.id.menu_setting -> CaptivePortalSettingAlertDialog(this)
                R.id.menu_feedback -> startActivity(Intent(this, FeedbackActivity::class.java))
                else -> {}
            }
            return@setOnMenuItemClickListener true
        }

        views.findViewById<RecyclerView>(R.id.my_recyclerview).apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = MyRecyclerViewAdapter(getData(), this@MainActivity)
            addItemDecoration(MyItemDecoration(25, 40))
        }

        BottomSheetDialog(this, R.style.Theme_MyBottomSheetDialog).apply {
            setContentView(views)
            setOnCancelListener { this@MainActivity.finish() }
            show()
        }
    }
}