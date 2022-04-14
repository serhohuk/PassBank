package com.serhohuk.passbank.domain.usecase

import com.serhohuk.passbank.data.models.PasswordData
import com.serhohuk.passbank.data.repositoriesimpl.SavedPasswordRepositoryImpl
import com.serhohuk.passbank.domain.models.PasswordEntity
import com.serhohuk.passbank.domain.repositories.SavedPasswordRepository
import com.serhohuk.passbank.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import org.koin.java.KoinJavaComponent.inject

class PasswordSaveUseCase {

    private val savedPasswordRepository by inject<SavedPasswordRepository>(SavedPasswordRepository::class.java)

    suspend fun execute(passwordEntity: PasswordEntity) {
        savedPasswordRepository.savePassword(passwordEntity)
    }

}