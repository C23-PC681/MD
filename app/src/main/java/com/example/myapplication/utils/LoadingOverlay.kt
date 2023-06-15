package com.example.myapplication.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.myapplication.R

class LoadingOverlay @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_loading_overlay, this, true)
        hideLoadingOverlay()
    }

    fun showLoadingOverlay() {
        visibility = View.VISIBLE
    }

    fun hideLoadingOverlay() {
        visibility = View.GONE
    }
}