package com.bqliang.nfushortcuts.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.databinding.ActivityLibraryCardBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.BarcodeFormat
import com.king.zxing.util.CodeUtils
import kotlin.concurrent.thread


class LibraryCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLibraryCardBinding

    private lateinit var barCode: ImageView
    private lateinit var text: TextView
    lateinit var fab: FloatingActionButton
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLibraryCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        barCode = binding.barCode
        text = binding.text
        fab = binding.settingFab

        thread {
            id = getSharedPreferences("app_data", MODE_PRIVATE)
                .getString("id", null)
            if (id.isNullOrBlank()){
                Snackbar.make(fab, R.string.tooltip_configure_id, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.ok){ }
                    .show()
            }else{
                createBarCode(id!!)
            }
        }


        setSupportActionBar(binding.toolbar)

        fab.setOnClickListener {

        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.library_card_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.id_setting -> LibraryCardSettingAlertDialog(this)
//            else -> return super.onOptionsItemSelected(item)
//        }
//        return true
//    }

    fun createBarCode(id: String) {
        thread {
            val bitmap = CodeUtils.createBarCode(id, BarcodeFormat.CODE_128, 800, 200)
            runOnUiThread {
                barCode.setImageBitmap(bitmap)
                text.text = id
            }
        }
    }

}