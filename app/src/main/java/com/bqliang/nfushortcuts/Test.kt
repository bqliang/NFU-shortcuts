package com.bqliang.nfushortcuts

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bqliang.nfushortcuts.model.Shortcut
import com.drakeet.multitype.ItemViewBinder

class Test : ItemViewBinder<Shortcut, ShortcutViewDelegate.ViewHolder>() {
    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ShortcutViewDelegate.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ShortcutViewDelegate.ViewHolder, item: Shortcut) {
        TODO("Not yet implemented")
    }

}