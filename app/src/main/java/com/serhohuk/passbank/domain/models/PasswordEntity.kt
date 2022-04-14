package com.serhohuk.passbank.domain.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId

open class PasswordEntity constructor(
    @PrimaryKey
    var id : Int = ObjectId().timestamp,
    @Required
    var name : String ="",
    var login : String? = null,
    @Required
    var key : String =""
) : RealmObject()
