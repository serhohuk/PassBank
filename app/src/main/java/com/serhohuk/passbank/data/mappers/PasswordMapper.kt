package com.serhohuk.passbank.data.mappers

import com.serhohuk.passbank.data.models.PasswordData
import com.serhohuk.passbank.domain.models.PasswordEntity

class PasswordMapper {

    fun toPasswordData(passwordEntity: PasswordEntity) : PasswordData{
        return PasswordData(
            id = passwordEntity.id,
            name = passwordEntity.name,
            login = passwordEntity.login,
            key = passwordEntity.key
        )
    }
}