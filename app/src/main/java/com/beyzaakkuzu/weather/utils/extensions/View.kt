package com.beyzaakkuzu.weather.utils.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager


fun View.hide(){
    visibility=GONE
}
fun View.show(){
    visibility= VISIBLE
}

fun hideKeyboard(activity:Activity){
    val view= activity.findViewById<View>(android.R.id.content)
    val inputManager: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE)as
            InputMethodManager
    inputManager.hideSoftInputFromWindow(view.windowToken,0)

}