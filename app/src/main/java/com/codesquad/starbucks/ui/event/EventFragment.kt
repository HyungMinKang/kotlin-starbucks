package com.codesquad.starbucks.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.codesquad.starbucks.R
import com.codesquad.starbucks.databinding.FragmentEventBinding
import com.codesquad.starbucks.ui.common.getNowDateTime
import com.codesquad.starbucks.ui.common.getTimeDiff
import org.koin.android.ext.android.inject


class EventFragment : Fragment() {

    private lateinit var binding: FragmentEventBinding
    private lateinit var navigator: NavController
    private val viewModel: EventViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigation.findNavController(view)
        binding.lifecycleOwner = viewLifecycleOwner
        checkBeforeSkipClicked()
        setSkipClickEvent()
        setCloseClickEvent()
        viewModel.eventInfo.observe(viewLifecycleOwner) { event ->
            binding.event = event
        }
    }


    private fun setSkipClickEvent() {
        binding.btnEventUndisplay.setOnClickListener {
            requireActivity().getSharedPreferences("ClickedBefore", AppCompatActivity.MODE_PRIVATE)
                .edit().apply {
                    putBoolean("clickedFlag", true)
                    putString("clickedTime", getNowDateTime())
                    apply()
                }
            navigator.navigate(R.id.action_eventFragment_to_frameFragment)
        }
    }

    private fun setCloseClickEvent() {
        binding.btnEventClose.setOnClickListener {
            navigator.navigate(R.id.action_eventFragment_to_frameFragment)
        }
    }

    private fun checkBeforeSkipClicked() {
        val sharedPreferences =
            requireActivity().getSharedPreferences("ClickedBefore", AppCompatActivity.MODE_PRIVATE)
        val flag = sharedPreferences.getBoolean("clickedFlag", false)
        val clickedTime = sharedPreferences.getString("clickedTime", null)
        clickedTime?.let {
            if (flag && getTimeDiff(clickedTime)) {
                navigator.navigate(R.id.action_eventFragment_to_frameFragment)
            }
        }
    }


}