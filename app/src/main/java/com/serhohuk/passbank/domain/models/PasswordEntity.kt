package com.serhohuk.passbank.domain.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId

data class PasswordEntity(
    @PrimaryKey
    var id : Int = ObjectId().timestamp,
    var name : String? = null,
    @Required
    var key : String
) : RealmObject()
