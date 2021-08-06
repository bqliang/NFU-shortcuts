package com.bqliang.nfushortcuts.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bqliang.nfushortcuts.ShortcutItem
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.adapter.MyRecyclerViewAdapter
import com.bqliang.nfushortcuts.tools.getData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.concurrent.thread

class MyBottomSheetDialogFragment:BottomSheetDialogFragment() {

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.finish()
    }

    private lateinit var myView:View
    private val itemList = ArrayList<ShortcutItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myView = inflater.inflate(R.layout.bottom_sheet_dialog_fragment, container, false)
        initRecyclerView()
        return myView
    }


    private fun initRecyclerView () {
        val myRecyclerView: RecyclerView = myView.findViewById(R.id.myRecyclerView)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val adapter = MyRecyclerViewAdapter(getData(), activity as Activity)
        myRecyclerView.layoutManager = layoutManager
        myRecyclerView.adapter = adapter
    }
}