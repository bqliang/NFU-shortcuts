package com.bqliang.nfushortcuts.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.model.Shortcut
import com.drakeet.multitype.ItemViewBinder

class ShortcutViewDelegate : ItemViewBinder<Shortcut, ShortcutViewDelegate.ViewHolder>() {


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val imageView: ImageView = itemView.findViewById(R.id.icon)
        private val textView: TextView = itemView.findViewById(R.id.text)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Shortcut) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        TODO("Not yet implemented")
    }
}