package com.bqliang.nfushortcuts.activity

import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bqliang.nfushortcuts.BuildConfig
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.tools.showToast
import com.bumptech.glide.Glide
import com.drakeet.about.*
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.Flags
import com.microsoft.appcenter.analytics.Analytics
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.google.android.material.snackbar.Snackbar


class AboutActivity : AbsAboutActivity() {

    private var mHits = LongArray(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Analytics.trackEvent("Open About Page")

        // 连续点击 5 次版本号复制 UUID
        findViewById<TextView>(R.id.version).setOnClickListener { view ->
            mHits.copyInto(mHits, startIndex = 1)
            mHits[4] = SystemClock.uptimeMillis()
            if (mHits[0] > mHits[4] - 1200){
                AppCenter.getInstallId().thenAccept {
                    val cm = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                    cm.setPrimaryClip(ClipData.newPlainText(packageName, it.toString()))
                }
                Snackbar.make(view, "UUID已复制到剪贴板", Snackbar.LENGTH_SHORT).show()
                mHits = LongArray(5)
            }
        }
    }

    override fun onCreateHeader(icon: ImageView, slogan: TextView, version: TextView) {
        Glide.with(this).load(R.drawable.about_page_app_icon).into(icon)
        version.text = BuildConfig.VERSION_NAME
        slogan.text = getString(R.string.app_name)
    }

    override fun layoutRes(): Int {
        // 为了解决某些 UI 问题，重写布局
        return R.layout.my_about_page
    }

    override fun onItemsCreated(items: MutableList<Any>) {
        items.apply {

            add(Category(getString(R.string.developer)))
            add(Contributor((R.drawable.develop), "bqliang",
                getString(R.string.developer_introduction), "https://github.com/bqliang"))

            add(Category(getString(R.string.special_thanks)))
            add(Contributor((R.drawable.my_girlfriend), getString(R.string.name_of_girlfriend),
                getString(R.string.my_girlfriend_introduction)))

            add(Category(getString(R.string.open_source_licenses)))
            add(License("Kotlin", "JetBrains",
                License.APACHE_2,"https://github.com/JetBrains/kotlin"))

            add(License("About Page", "drakeet",
                License.APACHE_2,"https://github.com/drakeet/about-page"))

            add(License("ZXing Lite", "jenly1314",
                License.APACHE_2,"https://github.com/jenly1314/ZXingLite"))

            add(License("Lottie Android", "Airbnb",
                License.APACHE_2,"https://github.com/airbnb/lottie-android"))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_page_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            android.R.id.home -> this.finish()
            R.id.menu_like -> {
                R.string.thanks_for_encouragement.showToast()
                Analytics.trackEvent("点赞")
            }
        }
        return true
    }
}