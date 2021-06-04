package com.example.mapproject

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapproject.adapter.VDetailsAdapter
import com.example.mapproject.databinding.ActivityMainBinding
import com.example.mapproject.service.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.vehicleData.observe(this, {
            val viewId = binding.vehRecView
            val adapter = VDetailsAdapter(it)
            viewId.adapter = adapter
            viewId.layoutManager = LinearLayoutManager(this)
        })
    }

}