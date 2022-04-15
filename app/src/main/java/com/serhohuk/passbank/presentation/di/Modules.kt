package com.serhohuk.passbank.presentation.di

import com.serhohuk.passbank.presentation.viewmodels.MainViewModel
import com.serhohuk.passbank.presentation.viewmodels.SavePasswordViewModel
import com.serhohuk.passbank.utils.SecureStorage
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        MainViewModel(get())
    }

    viewModel {
        SavePasswordViewModel(get())
    }
}

val securityModule = module {

    factory {
        SecureStorage(get())
    }
}