package com.serhohuk.passbank.domain.usecase

import com.serhohuk.passbank.data.models.PasswordData
import com.serhohuk.passbank.domain.repositories.SavedPasswordRepository
import com.serhohuk.passbank.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import org.koin.java.KoinJavaComponent

class PasswordGetUseCase {

    private val savedPasswordRepository by KoinJavaComponent.inject<SavedPasswordRepository>(SavedPasswordRepository::class.java
    )

    suspend fun executeAll(): Flow<Result<List<PasswordData>>> {
        return savedPasswordRepository.getAllPasswords()
    }

    suspend fun execute(key : String): Flow<Result<PasswordData>> {
        return savedPasswordRepository.getByKey(key)
    }

}