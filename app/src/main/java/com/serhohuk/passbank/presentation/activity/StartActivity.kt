package com.serhohuk.passbank.presentation.activity

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.text.Editable
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.serhohuk.passbank.presentation.adapters.NumPadAdapter
import com.serhohuk.passbank.databinding.ActivityStartBinding
import com.serhohuk.passbank.utils.SecureStorage
import com.serhohuk.passbank.utils.TextChangedWatcher
import com.serhohuk.passbank.utils.toast

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding
    private var cancellationSignal : CancellationSignal? = null
    private lateinit var  secureStorage : SecureStorage

    private val authenticationCallback : BiometricPrompt.AuthenticationCallback
    get() =
        object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                toast("Authentication Error")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                startActivity(Intent(this@StartActivity, MainActivity::class.java))
                finish()
            }

        }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        secureStorage = SecureStorage(this)
        if (secureStorage.getPasswordByKey(APP_START_PINCODE).isEmpty()){
            startActivity(Intent(this@StartActivity, PinCodeSetupActivity::class.java))
        }

        val adapter = NumPadAdapter(this)
        binding.gridView.adapter = adapter


        binding.gridView.setOnItemClickListener { _, _, position, _ ->
            if (position == 9) {
                checkBiometricSupport()
                val biometricPrompt = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Fingerprint")
                    .setSubtitle("Authentication required")
                    .setNegativeButtonText("Cancel")
                    .build()

                //biometricPrompt.authenticate(getCancellationSignal(),mainExecutor,authenticationCallback)
                BiometricPrompt(this, ContextCompat.getMainExecutor(this), authenticationCallback).authenticate(biometricPrompt)
            } else if(position == 11){
                val length = binding.passwordField.text.length
                if (binding.passwordField.text.isNotEmpty()) {
                    binding.passwordField.text.delete(length-1,length)
                }
            } else {
                val length = binding.passwordField.text.length
                if (length < 6){
                    binding.passwordField.setText(binding.passwordField.text.toString()+adapter.getItem(position))
                }
            }
        }


        binding.gridView.setOnItemLongClickListener { parent, view, position, id ->
            if (position == 11) {
                binding.passwordField.text.clear()
            }
            return@setOnItemLongClickListener true
        }

        binding.passwordField.addTextChangedListener(object : TextChangedWatcher{
            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                val text = s.toString()
                if (text.length==6){
                    if (secureStorage.getPasswordByKey(APP_START_PINCODE) == text){
                        startActivity(Intent(this@StartActivity, MainActivity::class.java))
                        finish()
                    } else {
                        binding.passwordField.text.clear()
                    }
                }
            }
        })


    }

    private fun checkBiometricSupport(): Boolean {
        val keyGuardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if (!keyGuardManager.isKeyguardSecure){
            toast("Fingerprint authentication has not been enabled in settings")
            return false
        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.USE_BIOMETRIC)
            !=PackageManager.PERMISSION_GRANTED){
            toast("Fingerprint authentication permissoion is not enabled")
            return false
        }

        return packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
    }
}