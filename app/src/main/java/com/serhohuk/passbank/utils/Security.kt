package com.serhohuk.passbank.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys
import androidx.security.crypto.MasterKeys.AES256_GCM_SPEC


fun getMasterKey(context: Context): MasterKey {
    return MasterKey.Builder(context)
        .setKeyGenParameterSpec(AES256_GCM_SPEC)
        .build()
}

abstract class Security (context: Context){
    val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    val sharedPref = EncryptedSharedPreferences.create(
        "secure_pref_bank",
        mainKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

class SecureStorage(context: Context) : Security(context) {

    fun savePasswordByKey(key : String, value : String){
        with(super.sharedPref.edit()){
            putString(key, value)
            apply()
        }
    }

    fun getPasswordByKey(key: String) : String{
        return sharedPref.getString(key, "") as String
    }
}