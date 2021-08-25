package com.bqliang.nfushortcuts.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.adapter.MyRecyclerViewAdapter
import com.bqliang.nfushortcuts.dialog.CaptivePortalSettingAlertDialog
import com.bqliang.nfushortcuts.model.ShortcutItem
import com.bqliang.nfushortcuts.view.MyItemDecoration
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


class MainActivity : AppCompatActivity() {

    companion object {
        val itemList = ArrayList<ShortcutItem>().apply {
            add(ShortcutItem(R.string.library_card, R.mipmap.library_card_circle))
            add(ShortcutItem(R.string.campus_bus, R.mipmap.campus_bus_circle))
            add(ShortcutItem(R.string.access_code, R.mipmap.access_code_circle))
            add(ShortcutItem(R.string.no_scan_pass, R.mipmap.no_scan_pass_circle))
            add(ShortcutItem(R.string.captive_portal_login, R.mipmap.login_circle))
            add(ShortcutItem(R.string.feed_developer, R.mipmap.feeding_developer_circle))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCenter.start(
            application, "f3e1fad6-dc60-4b23-8101-b7c98999bfdb",
            Analytics::class.java, Crashes::class.java
        )

        BottomSheetDialog(this, R.style.Theme_MyBottomSheetDialog).apply {
            setContentView(R.layout.bottom_sheet_dialog_layout)

            findViewById<RecyclerView>(R.id.my_recyclerview)?.apply {
                layoutManager = GridLayoutManager(this@MainActivity, 2)
                adapter = MyRecyclerViewAdapter(itemList, this@MainActivity)
                addItemDecoration(MyItemDecoration(25, 40))
            }

            show()

            findViewById<MaterialToolbar>(R.id.toolbar)?.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.menu_about -> startActivity(Intent(this@MainActivity, AboutActivity::class.java))
                    R.id.menu_setting -> CaptivePortalSettingAlertDialog(this@MainActivity)
                    R.id.menu_feedback -> startActivity(Intent(this@MainActivity, FeedbackActivity::class.java))
                    else -> {}
                }
                return@setOnMenuItemClickListener true
            }

            setOnCancelListener { this@MainActivity.finish() }
        }
    }
}