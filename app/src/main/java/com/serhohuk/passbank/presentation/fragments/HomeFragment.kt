package com.serhohuk.passbank.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serhohuk.passbank.R
import com.serhohuk.passbank.presentation.adapters.PasswordInfoAdapter


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homefragment, container, false)
    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val rec = view.findViewById<RecyclerView>(R.id.rv_passwords)
//        val adapter = PasswordInfoAdapter()
//        rec.adapter = adapter
//        rec.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
//    }
}