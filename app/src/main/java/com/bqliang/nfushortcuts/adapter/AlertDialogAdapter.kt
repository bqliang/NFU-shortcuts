package com.bqliang.nfushortcuts.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bqliang.nfushortcuts.model.ShortcutItem
import com.bqliang.nfushortcuts.R

class AlertDialogAdapter(
    activity: Activity,
     private val resourceId: Int,
     data:List<ShortcutItem>
): ArrayAdapter<ShortcutItem>(activity, resourceId, data) {

    inner class ViewHolder(val text:TextView, val icon:ImageView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val text:TextView = view.findViewById(R.id.text)
            val icon:ImageView = view.findViewById(R.id.icon)
            viewHolder = ViewHolder(text, icon)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position)
        if (item != null){
            viewHolder.text.setText(item.textResId)
            viewHolder.icon.setImageResource(item.iconResId)
        }
        return view
    }
}