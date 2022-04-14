package com.serhohuk.passbank.data.di

import com.serhohuk.passbank.data.repositoriesimpl.SavedPasswordRepositoryImpl
import com.serhohuk.passbank.domain.repositories.SavedPasswordRepository
import io.realm.RealmConfiguration
import org.koin.dsl.module

private val realmVersion = 1L

val dbModule = module {
    single<RealmConfiguration> {
        RealmConfiguration.Builder()
            .schemaVersion(realmVersion)
            .build()
    }

    factory<SavedPasswordRepository> {
        SavedPasswordRepositoryImpl()
    }
}
