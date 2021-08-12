package com.bqliang.nfushortcuts.adapter

import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bqliang.nfushortcuts.model.ShortcutItem
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.model.Shortcut
import com.bqliang.nfushortcuts.tools.MyApplication
import com.bqliang.nfushortcuts.tools.createPinnedShortcut
import com.bqliang.nfushortcuts.tools.getMyIntent
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.card.MaterialCardView

class MyRecyclerViewAdapter(private val data:List<ShortcutItem>, val activity: Activity):
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {
    // 如果仅有一个ViewHolder，即只有一种 itemView 的时候，就将类型指定为内部类唯一的 ViewHolder，
    // 否则指定为 RecyclerView.ViewHolder

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        private val itemIcon:ImageView = itemView.findViewById(R.id.icon)
        private val itemName:TextView = itemView.findViewById(R.id.text_id)
        private val materialCard = itemView.findViewById<MaterialCardView>(R.id.card_layout)

        init {
            materialCard.setOnClickListener {
                MyApplication.context.startActivity(getMyIntent(absoluteAdapterPosition))
                activity.finish()
            }

            materialCard.setOnLongClickListener { view ->
                if (absoluteAdapterPosition == 5){
                    "I am so hungry!".showToast()
                    return@setOnLongClickListener true
                }
                PopupMenu(activity, view)
                    .apply {
                        inflate(R.menu.recyclerview_menu)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) setForceShowIcon(true)
                        setOnMenuItemClickListener {
                            if (it.itemId == R.id.menu_create_pinned_shortcut){
                                when(absoluteAdapterPosition){
                                    0 -> createPinnedShortcut(R.string.library_card, R.mipmap.library_card_circle, getMyIntent(absoluteAdapterPosition), Shortcut.LIBRARY_CARD)
                                    1 -> createPinnedShortcut(R.string.campus_bus, R.mipmap.campus_bus_circle, getMyIntent(absoluteAdapterPosition), Shortcut.CAMPUS_BUS)
                                    2 -> createPinnedShortcut(R.string.access_code,R.mipmap.access_code_circle, getMyIntent(absoluteAdapterPosition), Shortcut.ACCESS_CODE)
                                    3 -> createPinnedShortcut(R.string.no_scan_pass, R.mipmap.no_scan_pass_circle, getMyIntent(absoluteAdapterPosition), Shortcut.QUICK_SCAN_QRCODE)
                                    4 -> createPinnedShortcut(R.string.captive_portal_login, R.mipmap.login_circle, getMyIntent(absoluteAdapterPosition), Shortcut.CAPTIVE_PORTAL_LOGIN)
                                }
                            }
                            return@setOnMenuItemClickListener true
                        }
                        show()
                    }
                return@setOnLongClickListener true
            }

        }

        fun bind(item: ShortcutItem){
            itemName.setText(item.textResId)
            itemIcon.setImageResource(item.iconResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shortcut_material_item, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}