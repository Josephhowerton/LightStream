package com.square.lightstream.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.square.lightstream.R
import com.square.lightstream.character.RMCharacter
import com.square.lightstream.databinding.FragmentCharactersBinding
import com.square.lightstream.view.adapter.RMCharacterAdapter
import com.square.lightstream.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(), RMCharacterAdapter.CharacterAdapterClickListener {
    private var _binding: FragmentCharactersBinding? = null
    val binding: FragmentCharactersBinding get() = _binding!!

    private val viewModel: CharactersViewModel by viewModels()
    private val adapter = RMCharacterAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel.getCharacters()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        viewModel.charactersLiveData.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }

        viewModel.shouldShowError.observe(viewLifecycleOwner) {
            if(it){
                showError()
            }
        }

        binding.recyclerCharacters.adapter = adapter

        return binding.root
    }

    override fun onItemClick(character: RMCharacter) {
        val bundle = bundleOf("characterId" to character.id)
        findNavController().navigate(R.id.action_charactersFragment_to_characterDetailsFragment, bundle)
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