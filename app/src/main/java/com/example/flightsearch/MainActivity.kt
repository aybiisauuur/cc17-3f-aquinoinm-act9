package com.example.flightsearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flightsearch.ui.FlightsAdapter
import com.example.flightsearch.viewmodel.FlightViewModel
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private val viewModel: FlightViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView
        binding.flightsRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = FlightsAdapter()
        binding.flightsRecyclerView.adapter = adapter

        // Observe search query flow
        lifecycleScope.launchWhenStarted {
            viewModel.searchQueryFlow.collect { query ->
                binding.searchEditText.setText(query ?: "")
            }
        }

        // Save query when text changes
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.saveSearchQuery(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

}

