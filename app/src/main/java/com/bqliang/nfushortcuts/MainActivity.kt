package com.bqliang.nfushortcuts

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity() {

    lateinit var mainAlertDialog:AlertDialog
    private val itemList = ArrayList<AlertDialogItem>()

    companion object{
        private val LIBRARY_CARD = "http://nfuedu.zftcloud.com/index/electronic_card/index.html?chInfo=ch_share__chsub_CopyLink"
        private val FEEDING_DEVELOPER ="https://qr.alipay.com/fkx18192oyczl2lnexuxud1"
        private val CAMPUS_BUS = "http://nfuedu.zftcloud.com/campusbus_index/ticket/index.html?chInfo=ch_share__chsub_CopyLink"
        private val ACCESS_CODE = "https://qr.alipay.com/s7x133604ff1cacyyk4fyd3"
        private val QUICK_SCAN_QRCODE = "http://nfuedu.zftcloud.com/index/travel_record/scanCode/path/1?chInfo=ch_share__chsub_CopyLink"
    }

    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)

        initData()
        val adapter = AlertDialogItemAdapter(this, R.layout.alertdialog_item, itemList)

        mainAlertDialog = MaterialAlertDialogBuilder(this)
            .setAdapter(
                adapter
            ) { dialog, which ->
                when (which) {
                    0 -> {
                        val uri = Uri.parse("https://github.com/bqliang")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                    }
                    1 -> openAlipay(FEEDING_DEVELOPER)
                    2 -> openAlipay(LIBRARY_CARD)
                    3 -> openAlipay(CAMPUS_BUS)
                    4 -> openAlipay(ACCESS_CODE)
                    5 -> openAlipay(QUICK_SCAN_QRCODE)
                }
                dialog.dismiss()
                this.finish()
            }
            .setOnCancelListener {
                this.finish()
            }
            .show()

        val view = LayoutInflater.from(this).inflate(R.layout.footer_view_layout, null)

        mainAlertDialog.listView.addFooterView(view)
        view.findViewById<MaterialCardView>(R.id.card).setOnClickListener {
            startForegroundService(Intent(this, MyService::class.java))
            mainAlertDialog.dismiss()
            this.finish()
        }
        view.findViewById<MaterialCardView>(R.id.card_of_setting).setOnClickListener{
            showSettingAlertDialog()
            mainAlertDialog.dismiss()
        }

        // 列表长按事件监听
        mainAlertDialog.listView.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { _, _, position, _ ->
                when(position){
                    0 -> "(´,,•∀•,,`)".showToast(Toast.LENGTH_LONG)
                    1 -> "I am so hungry!".showToast()
                    2 -> resources.getText(R.string.electronic_library_card).toString().showToast()
                    3 -> resources.getText(R.string.campus_bus).toString().showToast()
                    4 -> resources.getText(R.string.access_code).toString().showToast()
                    5 -> resources.getText(R.string.no_scan_pass).toString().showToast()
                }
                return@OnItemLongClickListener true
            }
    }


    private fun initData(){
        itemList.apply {
            add(AlertDialogItem(resources.getString(R.string.developer_github), R.mipmap.github_circle))
            add(AlertDialogItem(resources.getString(R.string.feeding_developer), R.mipmap.feeding_developer_circle))
            add(AlertDialogItem(resources.getString(R.string.electronic_library_card), R.mipmap.library_card_circle))
            add(AlertDialogItem(resources.getString(R.string.campus_bus), R.mipmap.campus_bus_circle))
            add(AlertDialogItem(resources.getString(R.string.access_code), R.mipmap.access_code_circle))
            add(AlertDialogItem(resources.getString(R.string.no_scan_pass), R.mipmap.no_scan_pass_circle))
        }
    }

    private fun openAlipay(opcode: String){
        val uri = Uri.parse("alipays://platformapi/startapp?appId=20000067&url=$opcode")
        val intent = Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent)
    }

    @SuppressLint("InflateParams")
    private fun showSettingAlertDialog(){
        val views = LayoutInflater.from(this).inflate(R.layout.id_pw_setting, null)
        val sp = getSharedPreferences("app_data", MODE_PRIVATE)

        val id = views.findViewById<TextView>(R.id.id_input)
        val password = views.findViewById<TextView>(R.id.pw_input)
        id.text = sp.getString("id","")
        password.text = sp.getString("password", "")

        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getText(R.string.setting_dialog_title))
            .setView(views)
            .setPositiveButton(resources.getText(R.string.save)) { dialog, _ ->
                saveInfo(
                    id.text.toString(),
                    password.text.toString()
                )
                resources.getText(R.string.save_successfully).toString().showToast()
                mainAlertDialog.show()
                dialog.dismiss()
            }
            .setNegativeButton(resources.getText(R.string.cancel)){ dialog, _ ->
                mainAlertDialog.show()
                dialog.dismiss()
            }
            .setOnCancelListener { dialog ->
                mainAlertDialog.show()
                dialog.dismiss()
            }
            .show()
    }

    private fun saveInfo(id:String, password:String){
        val sp = getSharedPreferences("app_data", MODE_PRIVATE)
        sp.edit().apply {
            putString("id",id)
            putString("password",password)
            commit()
        }
    }
}