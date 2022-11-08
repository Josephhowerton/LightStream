package com.square.lightstream.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.square.lightstream.R
import com.square.lightstream.databinding.FragmentLocationsBinding
import com.square.lightstream.location.Location
import com.square.lightstream.view.adapter.LocationAdapter
import com.square.lightstream.viewmodel.LocationsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsFragment : Fragment(), LocationAdapter.LocationAdapterClickListener {
    private var _binding: FragmentLocationsBinding? = null
    val binding: FragmentLocationsBinding get() = _binding!!

    private val viewModel: LocationsViewModel by viewModels()
    private val adapter = LocationAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel.getLocations()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)

        binding.recyclerLocation.adapter = adapter

        viewModel.locationLiveData.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }

        viewModel.shouldShowError.observe(viewLifecycleOwner){
            if (it) {
                showError()
            }
        }

        return binding.root
    }

    override fun onItemClick(location: Location) {
        val bundle = bundleOf("locationId" to location.id)
        findNavController().navigate(R.id.action_locationsFragment_to_locationDetailsFragment, bundle)
    }

    private fun showError() {
        AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setTitle(R.string.title_error)
            .setMessage(R.string.message_error)
            .setNegativeButton(R.string.title_error_button) { dialog, _ ->
                findNavController().navigateUp()
                viewModel.clearError()
                dialog.dismiss()
            }
    }
}