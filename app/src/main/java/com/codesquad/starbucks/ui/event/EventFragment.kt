package com.codesquad.starbucks.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.codesquad.starbucks.R
import com.codesquad.starbucks.databinding.FragmentEventBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventFragment : Fragment() {

    private lateinit var binding: FragmentEventBinding
    private lateinit var navigator: NavController
    private val viewModel: EventViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_event, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigation.findNavController(view)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.eventInfo.observe(viewLifecycleOwner){ event->
            binding.event= event
        }
    }

}