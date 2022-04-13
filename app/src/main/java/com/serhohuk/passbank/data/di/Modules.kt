package com.serhohuk.passbank.data.di

import io.realm.RealmConfiguration
import org.koin.dsl.module

private val realmVersion = 1L

val dbModule = module {
    single {
        RealmConfiguration.Builder()
            .schemaVersion(realmVersion)
            .build()
    }

    factory {

    }
}
