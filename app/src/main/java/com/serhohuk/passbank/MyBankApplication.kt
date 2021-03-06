package com.serhohuk.passbank

import android.app.Application
import com.serhohuk.passbank.data.di.dbModule
import com.serhohuk.passbank.domain.di.useCases
import com.serhohuk.passbank.presentation.di.presentationModule
import com.serhohuk.passbank.presentation.di.securityModule
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyBankApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@MyBankApplication)
            modules(listOf(dbModule, useCases,presentationModule, securityModule))
        }
    }
}