package com.esaudev.graphqlapollo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.esaudev.graphqlapollo.adapter.CharactersListAdapter
import com.esaudev.graphqlapollo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CharacterViewModel by viewModels()
    private val charactersListAdapter = CharactersListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.charactersList.apply { adapter = charactersListAdapter }
        observeData()
        viewModel.queryCharactersList()
    }

    private fun observeData() {
        viewModel.charactersList.observe(this) { response ->
            when (response) {
                is ViewState.Success -> {
                    if (response.value?.data?.characters?.results?.size == 0) {
                        charactersListAdapter.submitList(emptyList())
                        return@observe
                    }
                    val results = response.value?.data?.characters?.results
                    charactersListAdapter.submitList(results)
                }
                else -> Unit
            }
        }
    }
}