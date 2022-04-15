package com.serhohuk.passbank.domain.repositories

import com.serhohuk.passbank.data.models.PasswordData
import com.serhohuk.passbank.domain.models.PasswordEntity
import com.serhohuk.passbank.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface SavedPasswordRepository {

    suspend fun savePassword(entity : PasswordEntity)

    suspend fun getAllPasswords() : StateFlow<List<PasswordData>>

    suspend fun getByKey(key : String) : Flow<Result<PasswordData>>
}