package com.bqliang.nfushortcuts.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bqliang.nfushortcuts.model.ShortcutItem
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.activity.AboutActivity
import com.bqliang.nfushortcuts.activity.FeedbackActivity
import com.bqliang.nfushortcuts.model.Shortcut
import com.bqliang.nfushortcuts.activity.TempActivity
import com.bqliang.nfushortcuts.dialog.CaptivePortalSettingAlertDialog
import com.bqliang.nfushortcuts.tools.MyApplication
import com.bqliang.nfushortcuts.tools.createPinnedShortcut
import com.bqliang.nfushortcuts.tools.getMyIntent
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.card.MaterialCardView
import kotlin.concurrent.thread

class MyRecyclerViewAdapter(private val data:List<ShortcutItem>, val activity: Activity):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // 如果仅有一个ViewHolder，即只有一种 itemView 的时候，就将类型指定为内部类唯一的 ViewHolder，
    // 否则指定为 RecyclerView.ViewHolder，这时必须重写 onBindViewHolder 方法，
    // 并在这个方法当中去绑定数据，

    companion object{
        private const val ITEM_TYPE_NORMAL = 1000
        private const val ITEM_TYPE_HEADER = 1001
    }

    inner class NormalViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        private val imageView:ImageView = itemView.findViewById(R.id.icon)
        private val textView:TextView = itemView.findViewById(R.id.text)

        init {
            itemView.setOnClickListener {
                MyApplication.context.startActivity(getMyIntent(absoluteAdapterPosition))
                activity.finish()
            }

            itemView.setOnLongClickListener {
                when(absoluteAdapterPosition){
                    // 0 被 head 占位
                    0 -> { }
                    1 -> createPinnedShortcut(R.string.library_card, R.mipmap.library_card_circle, getMyIntent(absoluteAdapterPosition), Shortcut.LIBRARY_CARD)
                    2 -> createPinnedShortcut(R.string.campus_bus, R.mipmap.campus_bus_circle, getMyIntent(absoluteAdapterPosition), Shortcut.CAMPUS_BUS)
                    3 -> createPinnedShortcut(R.string.access_code,R.mipmap.access_code_circle, getMyIntent(absoluteAdapterPosition), Shortcut.ACCESS_CODE)
                    4 -> createPinnedShortcut(R.string.no_scan_pass, R.mipmap.no_scan_pass_circle, getMyIntent(absoluteAdapterPosition), Shortcut.QUICK_SCAN_QRCODE)
                    5 -> createPinnedShortcut(R.string.captive_portal_login, R.mipmap.login_circle, getMyIntent(absoluteAdapterPosition), Shortcut.CAPTIVE_PORTAL_LOGIN)
                    6 -> "I am so hungry!".showToast()
                }
                return@setOnLongClickListener true
            }
        }

        fun bind(item: ShortcutItem){
            textView.setText(item.textResId)
            imageView.setImageResource(item.iconResId)
            //if (item.textResId == R.string.captive_portal_login) textView.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        }
    }


    inner class HeaderViewHolder(headView : View) : RecyclerView.ViewHolder(headView){
        private val toolbar = headView.findViewById<MaterialToolbar>(R.id.toolbar)

        init {
            toolbar.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.menu_about -> {
                        val intent = Intent(activity, AboutActivity::class.java)
                        activity.startActivity(intent)
                    }
                    R.id.menu_setting -> CaptivePortalSettingAlertDialog(activity)
                    R.id.menu_feedback -> activity.startActivity(Intent(activity, FeedbackActivity::class.java))
                    else -> {}
                }
                return@setOnMenuItemClickListener true
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType){
            ITEM_TYPE_HEADER -> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.header_view_layout, parent, false))
            else -> NormalViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shortcut_item_view, parent, false))
        }

    override fun getItemCount() = data.size + 1

    override fun getItemViewType(position: Int) = when(position){
        0 -> ITEM_TYPE_HEADER
        else -> ITEM_TYPE_NORMAL
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NormalViewHolder) holder.bind(data[position - 1])
    }
}