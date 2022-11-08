package com.square.lightstream.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.square.lightstream.databinding.FragmentCharacterDetailsBinding
import com.square.lightstream.view.adapter.StringAdapter
import com.square.lightstream.viewmodel.CharacterDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment(), StringAdapter.StringAdapterClickListener  {
    private var _binding: FragmentCharacterDetailsBinding? = null
    val binding: FragmentCharacterDetailsBinding get() = _binding!!

    private val viewModel: CharacterDetailsViewModel by viewModels()
    private val adapter: StringAdapter = StringAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        arguments?.let {
            val id = it.getInt("characterId")
            viewModel.getCharacter(id)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerViewEpisodes.adapter = adapter
        viewModel.characterLiveData?.observe(viewLifecycleOwner) {
            adapter.updateList(it.episode)
        }

        return binding.root
    }

    override fun onItemClick(residents: String) {

    }
}