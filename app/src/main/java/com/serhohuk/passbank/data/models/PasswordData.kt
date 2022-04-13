package com.serhohuk.passbank.data.models

import io.realm.annotations.Required
import org.bson.types.ObjectId

data class PasswordData(
    var id : Int = ObjectId().timestamp,
    var name : String? = null,
    var key : String
)
