package com.serhohuk.passbank.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


object KeyboardUtils {

    fun showKeyboard(theView: View) {
        val context = theView.context
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(theView, 0)
    }

    suspend fun showKeyboardDelayed(theView: View) {
        delay(200)
        showKeyboard(theView)
    }

    fun hideKeyboard(theView: View) {
        val context: Context = theView.context
        val service: Any = context.getSystemService(Context.INPUT_METHOD_SERVICE)
        val imm: InputMethodManager = service as InputMethodManager
        imm.hideSoftInputFromWindow(theView.windowToken, 0)
    }

    fun hideKeyboard(context: Context?) {
        // close keyboard, use activity context, not application context
        if (context == null) return
        val view = (context as Activity).window.currentFocus
        view?.let { hideKeyboard(it) }
    }

    suspend fun hideKeyboardDelayed(theView: View) {
        delay(200)
        hideKeyboard(theView)
    }
}