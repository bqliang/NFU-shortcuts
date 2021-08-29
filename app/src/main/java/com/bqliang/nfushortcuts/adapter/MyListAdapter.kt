package com.bqliang.nfushortcuts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.activity.CallXiaobaiActivity
import com.bqliang.nfushortcuts.model.Driver
import com.bqliang.nfushortcuts.tools.ClipboardUtil
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class MyListAdapter(val activity: CallXiaobaiActivity, val resourceId: Int, data: List<Driver>) :
    ArrayAdapter<Driver>(activity, resourceId, data) {

    inner class ViewHolder(val name: MaterialTextView, val phoneNum: MaterialTextView, val icon: ImageView, val cardView: MaterialCardView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val view : View = convertView ?: LayoutInflater.from(context).inflate(resourceId, parent, false)
        if (convertView == null){
            val driverName = view.findViewById<MaterialTextView>(R.id.driver_name)
            val driverPhoneNum = view.findViewById<MaterialTextView>(R.id.phone_num)
            val icon = view.findViewById<ImageView>(R.id.driver_icon)
            val cardView = view.findViewById<MaterialCardView>(R.id.driver_card_view)
            viewHolder = ViewHolder(driverName, driverPhoneNum, icon, cardView)
            view.tag = viewHolder
        }else{
            viewHolder = view.tag as ViewHolder
        }
        val driver = getItem(position)
        if (driver != null){
            viewHolder.apply {
                name.setText(driver.nameResId)
                phoneNum.text = driver.phoneNum
                icon.setImageResource(driver.iconResId)
                cardView.setOnClickListener {
                    activity.call(driver.phoneNum)
                }
                cardView.setOnLongClickListener {
                    R.string.phone_num_copied.showToast()
                    ClipboardUtil.copyText(driver.phoneNum)
                    return@setOnLongClickListener true
                }
            }
        }
        return view
    }

}