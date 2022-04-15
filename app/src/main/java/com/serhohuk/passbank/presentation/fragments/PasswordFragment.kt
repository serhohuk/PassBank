package com.serhohuk.passbank.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.serhohuk.passbank.R
import com.serhohuk.passbank.databinding.FragmentPasswordBinding
import com.serhohuk.passbank.domain.utils.Result
import com.serhohuk.passbank.presentation.viewmodels.MainViewModel
import com.serhohuk.passbank.utils.SecureStorage
import com.serhohuk.passbank.utils.toast
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class PasswordFragment : Fragment() {

    private var _binding : FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MainViewModel by viewModel()

    private val navArgs : PasswordFragmentArgs by navArgs()
    private val secureStorage : SecureStorage by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etLogin.editText?.setText(navArgs.passData.login.toString())
        binding.etServiceName.editText?.setText(navArgs.passData.name)
        binding.etPassword.editText?.setText(secureStorage.getPasswordByKey(navArgs.passData.key))
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}