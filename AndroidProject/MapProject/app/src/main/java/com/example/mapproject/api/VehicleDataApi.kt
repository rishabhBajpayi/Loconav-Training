package com.example.mapproject.api

import com.example.mapproject.model.Vehicles
import retrofit2.Call
import retrofit2.http.GET

interface VehicleDataApi {

    @GET("/v3/f55d3019-2997-4153-b029-ed5aeaee8bfa")
    fun getData() : Call<Vehicles>
}