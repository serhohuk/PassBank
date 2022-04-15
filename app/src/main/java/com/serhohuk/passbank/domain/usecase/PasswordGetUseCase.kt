package com.serhohuk.passbank.domain.usecase

import com.serhohuk.passbank.data.models.PasswordData
import com.serhohuk.passbank.domain.repositories.SavedPasswordRepository
import com.serhohuk.passbank.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject

class PasswordGetUseCase(private val savedPasswordRepository :SavedPasswordRepository) {

    //private val savedPasswordRepository by inject<SavedPasswordRepository>(SavedPasswordRepository::class.java)

    suspend fun executeAll(): StateFlow<List<PasswordData>> {
        return savedPasswordRepository.getAllPasswords()
    }

    suspend fun execute(key : String): Flow<Result<PasswordData>> {
        return savedPasswordRepository.getByKey(key)
    }

}