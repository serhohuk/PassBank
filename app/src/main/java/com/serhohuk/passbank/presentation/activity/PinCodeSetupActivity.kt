package com.serhohuk.passbank.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.serhohuk.passbank.R
import com.serhohuk.passbank.databinding.ActivityPincodeSetupBinding
import com.serhohuk.passbank.utils.KeyboardUtils
import com.serhohuk.passbank.utils.SecureStorage
import com.serhohuk.passbank.utils.TextChangedWatcher
import kotlinx.coroutines.launch


const val APP_START_PINCODE = "app_pincode"

class PinCodeSetupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPincodeSetupBinding
    private var prevPsswrd = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPincodeSetupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.etPassword.imeOptions = binding.etPassword.imeOptions or EditorInfo.IME_ACTION_NONE
        binding.etPassword.addTextChangedListener(object : TextChangedWatcher{
            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                val text = s.toString()
                if (text.length==6){
                    handleEnteredPassword(text)
                }
            }
        })

        binding.ivBack.setOnClickListener {
            prevPsswrd = ""
            binding.etPassword.text.clear()
            KeyboardUtils.hideKeyboard(binding.etPassword)
            binding.ivBack.visibility = View.INVISIBLE
        }

    }

    private fun handleEnteredPassword(text: String) {
        if (prevPsswrd.isEmpty()){
            prevPsswrd = text
            binding.etPassword.text.clear()
            KeyboardUtils.hideKeyboard(binding.etPassword)
            binding.ivBack.visibility = View.VISIBLE
        } else {
            if (prevPsswrd == text){
                KeyboardUtils.hideKeyboard(binding.etPassword)
                val storage = SecureStorage(this)
                storage.savePasswordByKey(APP_START_PINCODE,text)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                showError()
                binding.ivBack.visibility = View.INVISIBLE
                binding.etPassword.text.clear()
                prevPsswrd = ""
            }
        }
    }

    private fun showError(){
        lifecycleScope.launch {
            binding.tvPsswdStatus.visibility = View.VISIBLE
            binding.tvPsswdStatus.animation = AnimationUtils.loadAnimation(this@PinCodeSetupActivity,R.anim.shake)
            binding.tvPsswdStatus.visibility = View.INVISIBLE
        }
    }
}