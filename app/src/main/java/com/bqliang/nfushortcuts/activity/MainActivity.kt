package com.bqliang.nfushortcuts.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.adapter.MyRecyclerViewAdapter
import com.bqliang.nfushortcuts.dialog.CaptivePortalSettingAlertDialog
import com.bqliang.nfushortcuts.model.ShortcutItem
import com.bqliang.nfushortcuts.model.Weather
import com.bqliang.nfushortcuts.model.WeatherViewModel
import com.bqliang.nfushortcuts.model.getSky
import com.bqliang.nfushortcuts.tools.ClipboardUtil
import com.bqliang.nfushortcuts.tools.mmkv
import com.bqliang.nfushortcuts.view.MyItemDecoration
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.microsoft.appcenter.distribute.Distribute

class MainActivity : AppCompatActivity() {
    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var toolbar: MaterialToolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var menuItem: MenuItem
    private var spanCount = MutableLiveData<Int>()
    private val decorationOfLinearLayout : MyItemDecoration by lazy { MyItemDecoration(1) }
    private val decorationOfGridLayout : MyItemDecoration by lazy { MyItemDecoration(2) }
    private val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    companion object {
        val itemList = ArrayList<ShortcutItem>().apply {
            add(ShortcutItem(R.string.library_card, R.mipmap.item_library_card))
            add(ShortcutItem(R.string.campus_bus, R.mipmap.item_campus_bus))
            add(ShortcutItem(R.string.access_code, R.mipmap.item_access_code))
            add(ShortcutItem(R.string.no_scan_pass, R.mipmap.item_no_scan_pass))
            add(ShortcutItem(R.string.captive_portal_login, R.mipmap.item_captive_portal_login))
            add(ShortcutItem(R.string.feed_developer, R.mipmap.item_feed_developer))
            add(ShortcutItem(R.string.kfc, R.mipmap.item_kfc))
            add(ShortcutItem(R.string.alipay_code, R.mipmap.item_alipay_code))
            add(ShortcutItem(R.string.health_code, R.mipmap.item_health_code))
            add(ShortcutItem(R.string.call_xiaobao, R.mipmap.item_call_xiaobai))
            add(ShortcutItem(R.string.cainiao_guoguo, R.mipmap.item_cainiao_guoguo))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.weatherLiveData.observe(this) { result ->
            val weather = result.getOrNull()
            if (weather != null){
                showWeatherInfo(weather)
            }else {
                result.exceptionOrNull()?.printStackTrace()
            }
        }
        viewModel.refreshWeather()

        bottomSheetDialog = BottomSheetDialog(this, R.style.Theme_MyBottomSheetDialog).apply {
            setContentView(R.layout.bottom_sheet_dialog_layout)

            recyclerView = findViewById(R.id.my_recyclerview)!!
            recyclerView.adapter = MyRecyclerViewAdapter(itemList, this@MainActivity)
            toolbar = findViewById(R.id.toolbar)!!
            menuItem = toolbar.menu?.findItem(R.id.menu_switch_layout)!!
            spanCount.observe(this@MainActivity){
                setRecyclerView()
                setMenuIcon()
                mmkv.encode("span_count", it)
            }
            spanCount.value = mmkv.decodeInt("span_count", 2)

            show()

            toolbar.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.menu_switch_layout -> { spanCount.value = if (spanCount.value == 1) 2 else 1 }
                    R.id.menu_about -> startActivity(Intent(this@MainActivity, AboutActivity::class.java))
                    R.id.menu_setting -> CaptivePortalSettingAlertDialog(this@MainActivity)
                    R.id.menu_feedback -> startActivity(Intent(this@MainActivity, FeedbackActivity::class.java))
                    R.id.menu_share -> {
                        ClipboardUtil.copyText("https://www.pgyer.com/ynis")
                        MaterialAlertDialogBuilder(this@MainActivity)
                            .setTitle(R.string.share)
                            .setMessage(R.string.share_dialog_message)
                            .setPositiveButton(R.string.ok) { _, _ ->
                                val uri = Uri.parse("weixin://")
                                val intent = Intent(Intent.ACTION_VIEW, uri)
                                startActivity(intent)
                            }
                            .setNegativeButton(R.string.cancel){ _, _ -> }
                            .show()
                    }
                    R.id.menu_check_updates -> Distribute.checkForUpdate()
                    else -> {}
                }
                return@setOnMenuItemClickListener true
            }

            setOnCancelListener { this@MainActivity.finish() }
        }
    }

    private fun setRecyclerView(){
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, spanCount.value!!)
            if (spanCount.value == 1) {
                removeItemDecoration(decorationOfGridLayout)
                addItemDecoration(decorationOfLinearLayout)
            }
            else{
                removeItemDecoration(decorationOfLinearLayout)
                addItemDecoration(decorationOfGridLayout)
            }
        }
    }

    private fun setMenuIcon(){
        val iconResId = if (spanCount.value == 1) R.drawable.ic_grid_view else R.drawable.ic_list_view
        menuItem.icon = ContextCompat.getDrawable(this, iconResId)
    }

    private fun showWeatherInfo(weather: Weather){
        val realtime = weather.realtime
        val currentTemp = "${realtime.temperature} ??C"
        val currentSky = getString(getSky(realtime.skycon).infoStrResId)
        toolbar.title = "$currentSky $currentTemp"
        realtime.lifeIndex.ultraviolet.description.let {
            if (it != "None" && it !=  "???"){
                toolbar.subtitle = getString(R.string.weather_ultraviolet) + it
            }
        }
    }
}