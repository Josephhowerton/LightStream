package com.square.lightstream.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.square.lightstream.R
import com.square.lightstream.databinding.FragmentWelcomeBinding
import com.square.lightstream.util.WhereTo
import com.square.lightstream.viewmodel.WelcomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragment: Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding get() = _binding!!

    private val viewModel: WelcomeViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel

        viewModel.whereToLiveData.observe(viewLifecycleOwner) {
            when(it){
                WhereTo.CharacterList -> {
                    findNavController().navigate(R.id.action_welcomeFragment_to_charactersFragment)
                    viewModel.clearNavigation()
                }
                WhereTo.LocationList -> {
                    findNavController().navigate(R.id.action_welcomeFragment_to_locationsFragment)
                    viewModel.clearNavigation()
                }
                else -> {}
            }

        }

        return binding.root
    }
}