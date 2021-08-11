package com.bqliang.nfushortcuts.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bqliang.nfushortcuts.view.MySpanSizeLookup
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.activity.AboutActivity
import com.bqliang.nfushortcuts.activity.FeedbackActivity
import com.bqliang.nfushortcuts.adapter.MyRecyclerViewAdapter
import com.bqliang.nfushortcuts.dialog.CaptivePortalSettingAlertDialog
import com.bqliang.nfushortcuts.view.MyItemDecoration
import com.bqliang.nfushortcuts.tools.getData
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetDialogFragment:BottomSheetDialogFragment() {

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.finish()
    }

    private lateinit var views:View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        views = inflater.inflate(R.layout.bottom_sheet_dialog_fragment, container, false)
        views.findViewById<MaterialToolbar>(R.id.toolbar).setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_about -> {
                    val intent = Intent(activity, AboutActivity::class.java)
                    activity?.startActivity(intent)
                }
                R.id.menu_setting -> CaptivePortalSettingAlertDialog(activity!!)
                R.id.menu_feedback -> activity?.startActivity(Intent(activity, FeedbackActivity::class.java))
                else -> {}
            }
            return@setOnMenuItemClickListener true
        }
        initRecyclerView()
        return views
    }


    private fun initRecyclerView () {
//        val myRecyclerView: RecyclerView = myView.findViewById(R.id.myRecyclerView)
//        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        val adapter = MyRecyclerViewAdapter(getData(), activity as Activity)
//        myRecyclerView.layoutManager = layoutManager
//        myRecyclerView.adapter = adapter

        val myRecyclerView: RecyclerView = views.findViewById(R.id.my_recyclerview)
        val layoutManager = GridLayoutManager(activity, 2)
        layoutManager.spanSizeLookup = MySpanSizeLookup()
        val adapter = MyRecyclerViewAdapter(getData(), activity as Activity)
        myRecyclerView.layoutManager = layoutManager
        myRecyclerView.adapter = adapter
        myRecyclerView.addItemDecoration(MyItemDecoration(30, 40))
    }
}