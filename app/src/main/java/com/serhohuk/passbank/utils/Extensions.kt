package com.serhohuk.passbank.utils

import android.content.Context
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun Float.toDips(context : Context) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics);

fun AppCompatActivity.toast(text : String){
    Toast.makeText(this,text, Toast.LENGTH_LONG).show()
}