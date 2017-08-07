package com.antonioleiva.weatherapp.extensions

import android.content.Context
import android.view.View
import android.widget.TextView
//对View类的扩展，返回一个上下文
val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)

//动画退出
fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

//动画进入
fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}