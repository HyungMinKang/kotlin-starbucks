package com.codesquad.starbucks.ui.whatNew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.codesquad.starbucks.R
import com.codesquad.starbucks.databinding.FragmentWhatNewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class WhatNewFragment : Fragment() {

    private lateinit var binding: FragmentWhatNewBinding
    private lateinit var navigator: NavController
    private val viewModel: WhatNewViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_what_new, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val whatNewAdapter = WhatNewAdapter()
        navigator = Navigation.findNavController(view)
        binding.rvWhatNewEvent.apply {
            adapter = whatNewAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.events.collect {
                whatNewAdapter.submitEvents(it)
            }
        }

        binding.btnWhatNewBack.setOnClickListener {
            navigator.navigate(R.id.action_whatNewFragment_to_homeFragment)
        }
    }

}