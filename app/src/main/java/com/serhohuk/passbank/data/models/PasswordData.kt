package com.serhohuk.passbank.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PasswordData(
    var id : Int,
    var name : String,
    var login : String?=null,
    var key : String
) : Parcelable
