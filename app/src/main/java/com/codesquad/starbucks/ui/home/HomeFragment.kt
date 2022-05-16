package com.codesquad.starbucks.ui.home

import android.icu.util.LocaleData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codesquad.starbucks.R
import com.codesquad.starbucks.databinding.FragmentHomefragmentBinding
import com.codesquad.starbucks.ui.common.getNowDateTime
import com.codesquad.starbucks.ui.common.getNowHour
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.time.LocalDateTime
import java.util.*
import kotlin.concurrent.timer

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomefragmentBinding
    private val viewModel: HomeViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_homefragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val personalRecommendAdapter= PersonalRecommendAdapter()

        val nowRecommendAdapter= NowRecommendAdapter()
        val homeEventAdapter= HomeEventAdapter()
        viewModel.eventInfo.observe(viewLifecycleOwner){
            binding.userName= it.displayName
            binding.mainEventImageUri= "${it.mainEventPath}${it.mainEventImagePath}"
            viewModel.getProduct(it.personalRecommendProducts)
            timer(period = 3600000, initialDelay = 0 ){
                CoroutineScope(Dispatchers.Main).launch{
                    binding.tvHomeNowRecommandTime.text= getNowHour()
                    viewModel.getNowRecommendProduct(it.nowRecommendProducts)
                }
            }
            viewModel.getHomeEvents()
        }

        binding.rvHomePersonalRecommendMenus.apply {
            adapter= personalRecommendAdapter
        }


        binding.rvHomeNowRecommendMenus.apply {
            adapter= nowRecommendAdapter
        }
        binding.rvHomeEvents.apply {
            adapter= homeEventAdapter
        }
        viewModel.products.observe(viewLifecycleOwner){
            personalRecommendAdapter.submitProducts(it)
        }

        viewModel.events.observe(viewLifecycleOwner){
            homeEventAdapter.submitProducts(it)
        }

        viewModel.nowProducts.observe(viewLifecycleOwner){
            nowRecommendAdapter.submitProducts(it)
        }
    }



}