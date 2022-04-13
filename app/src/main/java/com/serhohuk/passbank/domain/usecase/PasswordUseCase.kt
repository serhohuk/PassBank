package com.serhohuk.passbank.domain.usecase

import com.serhohuk.passbank.data.models.PasswordData
import com.serhohuk.passbank.domain.models.PasswordEntity
import com.serhohuk.passbank.domain.repositories.SavedPasswordRepository
import com.serhohuk.passbank.domain.utils.Result
import kotlinx.coroutines.flow.Flow

class PasswordUseCase (private val savedPasswordRepository: SavedPasswordRepository) {

    fun execute(passwordEntity: PasswordEntity): Flow<Result<PasswordData>> {
        return savedPasswordRepository.savePassword(passwordEntity)
    }

}