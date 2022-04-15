package com.serhohuk.passbank.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serhohuk.passbank.R
import com.serhohuk.passbank.databinding.FragmentHomefragmentBinding
import com.serhohuk.passbank.domain.utils.Result
import com.serhohuk.passbank.presentation.adapters.PasswordInfoAdapter
import com.serhohuk.passbank.presentation.viewmodels.MainViewModel
import com.serhohuk.passbank.utils.toast
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private var _binding : FragmentHomefragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel : MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomefragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fbAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homefragment_to_savePasswordFragment)
        }
        val adapter = PasswordInfoAdapter()
        binding.rvPasswords.adapter = adapter
        binding.rvPasswords.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        lifecycleScope.launchWhenStarted {
            viewModel.execute().collectLatest {
                when(it){
                    is Result.InProgress ->{
                        toast("loading")
                    }
                    is Result.Success -> {
                        adapter.items.submitList(it.data)
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}