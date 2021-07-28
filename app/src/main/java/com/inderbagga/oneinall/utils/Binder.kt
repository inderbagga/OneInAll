package com.inderbagga.oneinall.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object Binder {

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, visible: Boolean) {

        when (view ){
            is RecyclerView -> view.visibility = if (visible) View.GONE else View.VISIBLE
        }
    }
}