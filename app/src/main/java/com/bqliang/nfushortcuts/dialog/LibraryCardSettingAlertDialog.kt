package com.bqliang.nfushortcuts.dialog

import android.content.Context.MODE_PRIVATE
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.activity.LibraryCardActivity
import com.bqliang.nfushortcuts.tools.saveIdPassword
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LibraryCardSettingAlertDialog(activity: AppCompatActivity) {

    val activity = activity

    init {
        setAlertDialog()
    }

    private fun setAlertDialog() {

        val views = LayoutInflater.from(activity).inflate(R.layout.library_card_setting, null)
        val idInput = views.findViewById<TextView>(R.id.id_input)

        idInput.text = activity.getSharedPreferences("app_data", MODE_PRIVATE)
            .getString("id","")



        MaterialAlertDialogBuilder(activity)
            .setTitle(activity.getText(R.string.configure_id))
            .setView(views)
            .setPositiveButton(activity.getText(R.string.save)){ _, _ ->
                val id = idInput.text.toString()
                saveIdPassword(id)
                activity.getText(R.string.save_successfully).toString().showToast()
                (activity as LibraryCardActivity).createBarCode(id)
            }
            .show()
    }
}