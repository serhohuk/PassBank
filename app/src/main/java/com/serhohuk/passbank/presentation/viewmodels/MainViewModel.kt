package com.serhohuk.passbank.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhohuk.passbank.data.models.PasswordData
import com.serhohuk.passbank.domain.usecase.PasswordGetUseCase
import com.serhohuk.passbank.domain.utils.Result

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val passwordGetUseCase: PasswordGetUseCase) : ViewModel() {

    private val flowPasswords : MutableStateFlow<Result<List<PasswordData>>?> = MutableStateFlow(Result.Empty)
    val stateFlow = flowPasswords.asStateFlow()


    fun execute(): StateFlow<Result<List<PasswordData>>?> {
        flowPasswords.value = Result.InProgress
        viewModelScope.launch {
            flowPasswords.value = Result.Success(passwordGetUseCase.executeAll().value)
        }
        return stateFlow
    }

}