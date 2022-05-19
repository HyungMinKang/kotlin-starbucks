package com.codesquad.starbucks.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.codesquad.starbucks.R
import com.codesquad.starbucks.databinding.FragmentHomefragmentBinding
import com.codesquad.starbucks.ui.common.getNowHour
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.concurrent.timer

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomefragmentBinding
    private lateinit var navigator: NavController
    private val viewModel: HomeViewModel by inject()
    private val personalRecommendAdapter = PersonalRecommendAdapter()
    private val nowRecommendAdapter = NowRecommendAdapter()
    private val homeEventAdapter = HomeEventAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_homefragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigation.findNavController(view)
        setAdapters()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.eventInfo.collect {
                binding.userName = it.displayName
                binding.mainEventImageUri = "${it.mainEventPath}${it.mainEventImagePath}"
                viewModel.getProduct(it.personalRecommendProducts)
                timer(period = 3600000, initialDelay = 0) {
                    CoroutineScope(Dispatchers.Main).launch {
                        binding.tvHomeNowRecommandTime.text = getNowHour()
                        viewModel.getNowRecommendProduct(it.nowRecommendProducts)
                    }
                }
                viewModel.getHomeEvents()
            }
        }

        binding.btnHomeWhatNew.setOnClickListener {
            navigator.navigate(R.id.action_homeFragment_to_whatNewFragment)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collect {
                    personalRecommendAdapter.submitProducts(it)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.events.collect {
                homeEventAdapter.submitProducts(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.nowProducts.collect {
                    nowRecommendAdapter.submitProducts(it)
                }
            }
        }
    }

    private fun setAdapters() {
        binding.rvHomePersonalRecommendMenus.apply { adapter = personalRecommendAdapter }
        binding.rvHomeNowRecommendMenus.apply { adapter = nowRecommendAdapter }
        binding.rvHomeEvents.apply { adapter = homeEventAdapter }
    }

}