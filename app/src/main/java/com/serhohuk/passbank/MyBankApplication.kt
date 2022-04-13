package com.serhohuk.passbank

import android.app.Application
import io.realm.Realm

class MyBankApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}