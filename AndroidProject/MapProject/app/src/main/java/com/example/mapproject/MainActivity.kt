package com.example.mapproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapproject.adapter.VDetailsAdapter
import com.example.mapproject.api.VehicleDataApi
import com.example.mapproject.databinding.ActivityMainBinding
import com.example.mapproject.model.Vehicles
import com.example.mapproject.service.ApiServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val serviceGen = ApiServiceGenerator.buildService(VehicleDataApi::class.java)
        val call = serviceGen.getData()
        call.enqueue(object : Callback<Vehicles> {
            override fun onFailure(call: Call<Vehicles>, t: Throwable) {
                t.printStackTrace()
                println(t.message.toString())
                Toast.makeText(baseContext,t.message.toString(),Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<Vehicles>, response: Response<Vehicles>) {
                if (response.isSuccessful) {
                    println(response.body()?.data)
                    val viewId = binding.vehRecView
                    val adapter = response.body()?.let { VDetailsAdapter(it.data) }
                    viewId.adapter = adapter
                    viewId.layoutManager = LinearLayoutManager(baseContext)
                } else {
                    Toast.makeText(baseContext,response.message(),Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}