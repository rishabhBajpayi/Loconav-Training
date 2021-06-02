package com.example.mapproject.model

data class Vehicles( val success : Boolean, val data : List<VDetails>, val meta : Total)

data class VDetails( val last_location:LastLocation, val icon_details:IconDetails, val id :Int?, val icon_kind : String?, val vehicle_make : String? , val vehicle_model : String? , val display_number : String)

data class IconDetails( val icon_kind: String, val vehicle_icon_id:String, val is_custom: Boolean, val service_url:String)

data class Total( val total: Int)

data class LastLocation( val lat : Double? , val long : Double? , val orientation : Double? , val speed : Double? , val received_ts : Double?)