package com.leon.weatherapp

import android.support.v7.widget.Toolbar


interface ToolbarManager {

    val toolbar : Toolbar

    var toolbarTitle : String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)

    }
}