package com.square.lightstream.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.square.lightstream.databinding.FragmentLocationDetailsBinding
import com.square.lightstream.view.adapter.StringAdapter
import com.square.lightstream.viewmodel.LocationDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailsFragment : Fragment(), StringAdapter.StringAdapterClickListener {

    private var _binding: FragmentLocationDetailsBinding? = null
    val binding: FragmentLocationDetailsBinding get() = _binding!!

    private val viewModel: LocationDetailsViewModel by viewModels()
    private val adapter: StringAdapter = StringAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        arguments?.let {
            val id = it.getInt("locationId")
            viewModel.getLocation(id)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerViewResidents.adapter = adapter
        viewModel.locationLiveData?.observe(viewLifecycleOwner) {
            adapter.updateList(it.residents)
        }

        return binding.root
    }

    override fun onItemClick(residents: String) {

    }
}