package com.codesquad.starbucks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codesquad.starbucks.R
import com.codesquad.starbucks.databinding.FragmentFrameBinding
import com.google.android.material.tabs.TabLayoutMediator


class FrameFragment : Fragment() {

    private lateinit var binding:FragmentFrameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_frame, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter=  FragmentTypePagerAdapter(requireActivity())
        binding.vpFragment.adapter= adapter
        TabLayoutMediator(binding.tabLayout, binding.vpFragment,true,true, adapter).attach()
    }

}