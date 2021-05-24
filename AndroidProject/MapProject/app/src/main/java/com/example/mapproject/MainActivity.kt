package com.example.mapproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mapproject.adapter.VDetailsAdapter
import com.example.mapproject.api.VehicleDataApi
import com.example.mapproject.model.Vehicles
import com.example.mapproject.service.ApiServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var serviceGen = ApiServiceGenerator.buildService(VehicleDataApi::class.java)
        val call = serviceGen.getData()
        call.enqueue(object : Callback<Vehicles> {
            override fun onFailure(call: Call<Vehicles>, t: Throwable) {
                t.printStackTrace()
                println(t.message.toString())
            }
            override fun onResponse(call: Call<Vehicles>, response: Response<Vehicles>) {
                if (response.isSuccessful) {
                    val viewId = findViewById<RecyclerView>(R.id.vehicles_recycler_view)
                    val adapter = VDetailsAdapter(response.body()?.data!!)
                    viewId.adapter = adapter
                    viewId.layoutManager = LinearLayoutManager(baseContext)
                }else{
                    print(response.message())
                }
            }
        })
    }
}