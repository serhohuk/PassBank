package com.serhohuk.passbank.data.repositoriesimpl

import com.serhohuk.passbank.data.models.PasswordData
import com.serhohuk.passbank.domain.models.PasswordEntity
import com.serhohuk.passbank.domain.repositories.SavedPasswordRepository
import com.serhohuk.passbank.domain.utils.Result
import kotlinx.coroutines.flow.Flow

class SavedPasswordRepositoryImpl : SavedPasswordRepository {
    override fun savePassword(entity: PasswordEntity): Flow<Result<PasswordData>> {
        TODO("Not yet implemented")
    }
}