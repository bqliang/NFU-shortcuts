package com.bqliang.nfushortcuts.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bqliang.nfushortcuts.ShortcutItem
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.Shortcut
import com.bqliang.nfushortcuts.dialog.CaptivePortalSettingAlertDialog
import com.bqliang.nfushortcuts.service.MyService
import com.bqliang.nfushortcuts.tools.MyApplication
import com.bqliang.nfushortcuts.tools.createPinnedShortcut
import com.bqliang.nfushortcuts.tools.getMyIntent
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.card.MaterialCardView

class MyRecyclerViewAdapter(private val data:List<ShortcutItem>,val activity: Activity):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // 如果仅有一个ViewHolder，即只有一种 itemView 的时候，就将类型指定为内部类唯一的 ViewHolder，
    // 否则指定为 RecyclerView.ViewHolder，这时必须重写 onBindViewHolder 方法，
    // 并在这个方法当中去绑定数据，

    companion object{
        private const val ITEM_TYPE_NORMAL = 0
        private const val ITEM_TYPE_FOOTER = 1
    }

    inner class NormalViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        private val imageView:ImageView = itemView.findViewById(R.id.icon)
        private val textView:TextView = itemView.findViewById(R.id.text)

        init {
            itemView.setOnClickListener {
                MyApplication.context.startActivity(getMyIntent(adapterPosition))
                activity.finish()
            }

            itemView.setOnLongClickListener {
                when(adapterPosition){
                    0 -> "(´,,•∀•,,`)".showToast(Toast.LENGTH_LONG)
                    1 -> "I am so hungry!".showToast()
                    2 -> "\ud83c\udf39".showToast()
                    3 -> createPinnedShortcut(MyApplication.context.resources.getText(R.string.library_card).toString(), R.mipmap.library_card_circle, getMyIntent(adapterPosition), Shortcut.LIBRARY_CARD)
                    4 -> createPinnedShortcut(MyApplication.context.resources.getText(R.string.campus_bus).toString(), R.mipmap.campus_bus_circle, getMyIntent(adapterPosition), Shortcut.CAMPUS_BUS)
                    5 -> createPinnedShortcut(MyApplication.context.resources.getText(R.string.access_code).toString(),R.mipmap.access_code_circle, getMyIntent(adapterPosition), Shortcut.ACCESS_CODE)
                    6 -> createPinnedShortcut(MyApplication.context.resources.getText(R.string.no_scan_pass).toString(), R.mipmap.no_scan_pass_circle, getMyIntent(adapterPosition), Shortcut.QUICK_SCAN_QRCODE)
                }
                return@setOnLongClickListener true
            }
        }

        fun bind(item: ShortcutItem){
            textView.text = item.text
            imageView.setImageResource(item.iconResourceId)
        }
    }


    inner class FooterViewHolder(footerView: View): RecyclerView.ViewHolder(footerView){

        private val captivePortalItem: MaterialCardView = footerView.findViewById(R.id.card)
        private val settingIcon: MaterialCardView = footerView.findViewById(R.id.card_of_setting)

        init {
            captivePortalItem.setOnClickListener {
                activity.startForegroundService(Intent(activity, MyService::class.java))
                activity.finish()
            }

            captivePortalItem.setOnLongClickListener {
                createPinnedShortcut(activity.resources.getString(R.string.captive_portal_login), R.mipmap.login_circle, getMyIntent(7), Shortcut.CAPTIVE_PORTAL_LOGIN)
                return@setOnLongClickListener true
            }

            settingIcon.setOnClickListener{
                CaptivePortalSettingAlertDialog(activity)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE_NORMAL){
            NormalViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shortcut_item_view, parent, false))
        }else {
            FooterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.footer_view_layout, parent, false))
        }
    }


    override fun getItemCount() = data.size + 1

    override fun getItemViewType(position: Int) = if (position == itemCount - 1) ITEM_TYPE_FOOTER else ITEM_TYPE_NORMAL


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NormalViewHolder){
            holder.bind(data[position])
        }
    }
}