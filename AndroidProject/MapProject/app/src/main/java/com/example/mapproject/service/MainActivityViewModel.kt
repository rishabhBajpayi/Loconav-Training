package com.example.mapproject.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapproject.model.VDetails
import com.example.mapproject.repo.ApiServices

class MainActivityViewModel : ViewModel() {

    private var _vehicleData = MutableLiveData<List<VDetails>>()
    val vehicleData : LiveData<List<VDetails>>
        get() = _vehicleData

    init{
        _vehicleData.value = ApiServices.apiRequest()
    }

}