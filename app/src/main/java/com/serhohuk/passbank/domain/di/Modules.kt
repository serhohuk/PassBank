package com.serhohuk.passbank.domain.di

import com.serhohuk.passbank.domain.usecase.PasswordGetUseCase
import com.serhohuk.passbank.domain.usecase.PasswordSaveUseCase
import org.koin.dsl.module

val useCases = module {

    factory{
        PasswordGetUseCase(get())
    }

    factory {
        PasswordSaveUseCase(get())
    }

}