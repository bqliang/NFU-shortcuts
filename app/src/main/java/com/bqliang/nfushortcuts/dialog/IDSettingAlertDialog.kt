package com.bqliang.nfushortcuts.dialog

import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.activity.LibraryCardActivity
import com.bqliang.nfushortcuts.tools.mmkv
import com.bqliang.nfushortcuts.tools.showSnackBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class IDSettingAlertDialog(activity: AppCompatActivity) {

    val activity = activity

    init {
        setAlertDialog()
    }

    private fun setAlertDialog() {

        val views = LayoutInflater.from(activity).inflate(R.layout.library_card_setting, null)
        val idInput = views.findViewById<TextView>(R.id.id_input)

        idInput.text = mmkv.decodeString("id", "")

        MaterialAlertDialogBuilder(activity).apply {
            setTitle(R.string.configure_id)
            setView(views)
            setPositiveButton(R.string.save){ _, _ ->
                val id = idInput.text.toString()
                mmkv.encode("id", id)
                (activity as LibraryCardActivity).createBarCode(id)
                activity.getString(R.string.save_successfully).showSnackBar(activity.fab)
            }
            show()
        }
    }
}