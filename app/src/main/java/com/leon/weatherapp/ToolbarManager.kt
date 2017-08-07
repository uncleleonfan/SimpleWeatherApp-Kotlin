package com.leon.weatherapp

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.antonioleiva.weatherapp.extensions.slideEnter
import com.antonioleiva.weatherapp.extensions.slideExit


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

    fun attachScroll(recyclerView : RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }

}