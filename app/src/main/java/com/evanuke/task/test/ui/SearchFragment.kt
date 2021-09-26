package com.evanuke.task.test.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.evanuke.task.test.R
import com.evanuke.task.test.databinding.FragmentSearchBinding
import com.evanuke.task.test.ui.SearchRepositoryState.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding: FragmentSearchBinding
        get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    private val repositoryAdapter = RepositoryAdapter(clickListener = {
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
        startActivity(urlIntent)
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeUi()
        setupUi()
    }

    private fun setupUi() = with(binding) {
        searchField.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.requestRepositories(searchField.text.toString())
                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }

        recyclerView.adapter = repositoryAdapter
    }

    private fun subscribeUi() {
        viewModel.repositories.observe(viewLifecycleOwner) {
            when (it) {
                is Loading -> {
                    binding.progressbar.isVisible = true
                }
                is Error -> {
                    binding.progressbar.isGone = true
                    Snackbar.make(binding.root, getString(R.string.error_message), Snackbar.LENGTH_SHORT).show()
                }
                is Success -> {
                    binding.progressbar.isGone = true
                    repositoryAdapter.submitList(it.repositories)
                }
            }
        }
    }
}
