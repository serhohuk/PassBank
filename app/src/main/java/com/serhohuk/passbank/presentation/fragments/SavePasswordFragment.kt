package com.serhohuk.passbank.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.serhohuk.passbank.databinding.FragmentSavePasswordBinding
import com.serhohuk.passbank.domain.models.PasswordEntity
import com.serhohuk.passbank.presentation.viewmodels.SavePasswordViewModel
import com.serhohuk.passbank.utils.SecureStorage
import com.serhohuk.passbank.utils.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import java.util.regex.Pattern

class SavePasswordFragment : Fragment() {

    private var _binding : FragmentSavePasswordBinding? = null

    private val binding get() = _binding!!

    private val viewModel : SavePasswordViewModel by viewModel()

    private val securityStorage by inject<SecureStorage>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnSave.setOnClickListener {
            if (readyToSave()){
                savePassword()
            } else {
                toast("Fill all necessary fields")
            }
        }
    }


    private fun readyToSave() : Boolean{
        return (binding.etServiceName.editText?.text.toString().isNotEmpty() &&
                binding.etPassword.editText?.text.toString().length>=4
                && !Pattern.matches(".*\\p{InCyrillic}.*",
            binding.etPassword.editText?.text.toString()))
    }

    private fun savePassword(){
        val serviceName = binding.etServiceName.editText?.text.toString()
        val login = binding.etLogin.editText?.text.toString()
        val password = binding.etPassword.editText?.text.toString()
        val uuidKey = UUID.randomUUID().toString()
        securityStorage.savePasswordByKey(uuidKey,password)
        val passwordEntity = PasswordEntity(name = serviceName, login = login, key = uuidKey )
        viewModel.execute(passwordEntity)
        toast("Successfully saved")
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}