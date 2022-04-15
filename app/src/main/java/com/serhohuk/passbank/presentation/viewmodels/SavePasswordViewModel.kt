package com.serhohuk.passbank.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhohuk.passbank.domain.models.PasswordEntity
import com.serhohuk.passbank.domain.usecase.PasswordSaveUseCase
import kotlinx.coroutines.launch

class SavePasswordViewModel(
    private val passwordSaveUseCase: PasswordSaveUseCase
) : ViewModel() {


    fun execute(passwordEntity: PasswordEntity){
        viewModelScope.launch {
            passwordSaveUseCase.execute(passwordEntity)
        }
    }

}