package com.trainee.appinventiv.eccoscreens.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.trainee.appinventiv.eccoscreens.R
import com.trainee.appinventiv.eccoscreens.adapter.TutorialScreenAdapter
import com.trainee.appinventiv.eccoscreens.databinding.FragmentTutorialBinding
import com.trainee.appinventiv.eccoscreens.viewmodel.TutorialViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


class TutorialFragment : Fragment() {

    private lateinit var binding: FragmentTutorialBinding
    private val viewModel : TutorialViewModel by viewModels()
    private var currentPage :Int =0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTutorialBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intiListener()
        viewPagerSetUp()
        viewpagerCallBack()
    }

    private fun viewpagerCallBack() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position
                when(position){
                    0 ->{
                        viewModel.textListener.update { getString(R.string.t1)  }
                    }
                    1 ->{
                        viewModel.textListener.update { getString(R.string.t2) }
                    }
                    2->{
                        viewModel.textListener.update { getString(R.string.t3) }
                    }
                }
            }
        })
    }

    private fun viewPagerSetUp() {
        //viewModel.textListener = MutableStateFlow(resources.getString(R.string.t1))
        val adapter = TutorialScreenAdapter()
        adapter.setList(arrayListOf(
            R.drawable.tutorial_image1,
            R.drawable.tutorial_image2,
            R.drawable.tutorial_image3
        ))
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayoutDotIndicator, binding.viewPager){ _, _ ->
        }.attach()

    }

    private fun intiListener() {

        binding.btNext.setOnClickListener{
            if(viewModel.hideTutorial.value){
                findNavController().navigate(R.id.action_onBoardFragment_to_emailEnteredFragment)
            }
            else if(binding.viewPager.currentItem == 2){
                viewModel.hideTutorial.update { true }
                viewModel.textListener.update { getString(R.string.t4) }
            }
            else{
                binding.viewPager.currentItem = binding.viewPager.currentItem+1
            }
        }
    }


}