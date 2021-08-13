package com.bqliang.nfushortcuts.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bqliang.nfushortcuts.BuildConfig
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.tools.showToast
import com.drakeet.about.*


class AboutActivity : AbsAboutActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateHeader(icon: ImageView, slogan: TextView, version: TextView) {
        icon.setImageResource(R.drawable.about_page_app_icon)
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
            android.R.id.home -> returnMainactivity()
            R.id.menu_like -> R.string.thanks_for_encouragement.showToast()
        }
        return true
    }

    override fun onBackPressed() {
        returnMainactivity()
        super.onBackPressed()
    }

    private fun returnMainactivity(){
        this.finish()
    }
}