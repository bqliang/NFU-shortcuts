package com.bqliang.nfushortcuts.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.activity.MainActivity
import com.bqliang.nfushortcuts.dialog.CaptivePortalSettingAlertDialog
import com.bqliang.nfushortcuts.model.Shortcut
import com.bqliang.nfushortcuts.model.ShortcutItem
import com.bqliang.nfushortcuts.tools.*
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

class MyRecyclerViewAdapter(private val data:List<ShortcutItem>, val activity: MainActivity):
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {
    // 如果仅有一个ViewHolder，即只有一种 itemView 的时候，就将类型指定为内部类唯一的 ViewHolder，
    // 否则指定为 RecyclerView.ViewHolder

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        private val itemIcon:ImageView = itemView.findViewById(R.id.icon)
        private val itemName:TextView = itemView.findViewById(R.id.text_id)
        private val materialCard = itemView.findViewById<MaterialCardView>(R.id.card_layout)

        init {
            materialCard.setOnClickListener {
                activity.startActivity(getIntent(Shortcut.values()[adapterPosition]))
                activity.bottomSheetDialog.dismiss()
                activity.finish()
            }

            materialCard.setOnLongClickListener { view ->
                val selectItem = Shortcut.values()[adapterPosition]
                if (selectItem == Shortcut.FEED_DEVELOPER){
                    "I am so hungry!".showToast()
                    return@setOnLongClickListener true
                }
                PopupMenu(activity, view).apply {
                    inflate(R.menu.recyclerview_pop_menu)
                    if (selectItem == Shortcut.CAPTIVE_PORTAL_LOGIN) menu.findItem(R.id.recyclerview_menu_configure_account_info).isVisible = true
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) setForceShowIcon(true)
                    setOnMenuItemClickListener {
                        if (it.itemId == R.id.menu_create_pinned_shortcut){
                            createPinnedShortcut(selectItem, activity)
                        }else if (it.itemId == R.id.recyclerview_menu_configure_account_info){
                            CaptivePortalSettingAlertDialog(activity)
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
            Glide.with(MyApplication.context).load(item.iconResId).into(itemIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shortcut_material_item, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}