package com.serhohuk.passbank.data.repositoriesimpl

import com.serhohuk.passbank.data.mappers.PasswordMapper
import com.serhohuk.passbank.data.models.PasswordData
import com.serhohuk.passbank.domain.models.PasswordEntity
import com.serhohuk.passbank.domain.repositories.SavedPasswordRepository
import com.serhohuk.passbank.domain.utils.Result
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import org.koin.java.KoinJavaComponent.inject

class SavedPasswordRepositoryImpl : SavedPasswordRepository {

    private val config by inject<RealmConfiguration>(RealmConfiguration::class.java)

    override suspend fun savePassword(entity: PasswordEntity) {
        val realm = Realm.getInstance(config)

        realm.executeTransactionAwait(Dispatchers.IO) { transaction->
            transaction.insert(entity)
        }
    }

    override suspend fun getAllPasswords(): StateFlow<List<PasswordData>> {
        val realm = Realm.getInstance(config)
        val passwordsList = mutableListOf<PasswordData>()
        val stateFlow = MutableStateFlow(listOf<PasswordData>())

        realm.executeTransactionAwait(Dispatchers.IO){ transaction->
            passwordsList.addAll(
                transaction
                    .where(PasswordEntity::class.java)
                    .findAll()
                    .map {
                        PasswordMapper().toPasswordData(it)
                    }
            )
        }
        stateFlow.value = passwordsList
        return stateFlow.asStateFlow()
    }

    override suspend fun getByKey(key: String): Flow<Result<PasswordData>> {
        TODO("Not yet implemented")
    }
}