package com.serhohuk.passbank.data.models

import io.realm.annotations.Required
import org.bson.types.ObjectId

data class PasswordData(
    var id : Int,
    var name : String,
    var login : String?=null,
    var key : String
)
