package com.example.mapproject.repo

import com.example.mapproject.api.VehicleDataApi
import com.example.mapproject.model.VDetails
import com.example.mapproject.model.Vehicles
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServices{


    object ApiServiceGenerator {
        private val client = OkHttpClient.Builder().build()
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fun <T> buildService(service: Class<T>): T{
            return retrofit.create(service)
        }
    }

    fun apiRequest() : List<VDetails> {
        var data : MutableList<VDetails> = mutableListOf()
        val serviceGen = ApiServiceGenerator.buildService(VehicleDataApi::class.java)
        val call = serviceGen.getData()
        call.enqueue(object : Callback<Vehicles> {
            override fun onFailure(call: Call<Vehicles>, t: Throwable) {
                t.printStackTrace()
                println(t.message.toString())
            }
            override fun onResponse(call: Call<Vehicles>, response: Response<Vehicles>){
                if (response.isSuccessful) {
                    println(response.body()?.data)
                    data = response.body()?.data as MutableList<VDetails>
                } else {
                    println(response.message())
                }
            }
        })

        return data
    }
}