package com.example.mapproject

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mapproject.service.WebSocketViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private var newLatLng: DoubleArray ? = null
    private lateinit var display: String
    private var curLocMar: Marker? = null
    private var speed : Double = 0.0
    private val viewModel: WebSocketViewModel by viewModels()
    lateinit var mapFragment: SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val actionBar = supportActionBar
        actionBar?.title = "Vehicle Last Location"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        newLatLng = intent.getDoubleArrayExtra("lastLoc")
        display = intent.getStringExtra("display").toString()
        speed = intent.getDoubleExtra("speed",0.0)

        viewModel.initiateSocketConnection()

        mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.closeWebSocket()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val vehLatLng = LatLng(newLatLng?.get(0) ?: 0.0 , newLatLng?.get(1)?: 0.0)
        val zoomLevel = 18f
        curLocMar = map.addMarker(MarkerOptions().position(vehLatLng).title(display)
            .snippet("Speed : $speed")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.truck_as_icon)).flat(true))

        curLocMar?.showInfoWindow()
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(vehLatLng, zoomLevel))
        onMapLongPress(map)
        onPoiClicked(map)
        updateMarkerLocation()
    }

    private fun updateMarkerLocation() {
        viewModel.payload.observe(mapFragment.viewLifecycleOwner,{payLoad->
            curLocMar?.let {
                it.position = LatLng(payLoad.latitude,payLoad.longitude)
                it.snippet = "Speed : ${payLoad.speed}"
                it.rotation = payLoad.orientation.toFloat()
                map.moveCamera(CameraUpdateFactory.newLatLng(it.position))
                it.showInfoWindow()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.maps_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.normal_map -> {
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            true
        }
        R.id.hybrid_map -> {
            map.mapType = GoogleMap.MAP_TYPE_HYBRID
            true
        }
        R.id.satellite_map -> {
            map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            true
        }
        R.id.terrain_map -> {
            map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            true
        }
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun onMapLongPress(map: GoogleMap) {
        map.setOnMapLongClickListener {
            println(it)
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                it.latitude,
                it.longitude
            )
            map.addMarker(
                MarkerOptions().position(it).title(getString(R.string.dropped_pin))
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
            )
        }
    }

    private fun onPoiClicked(map: GoogleMap) {
        map.setOnPoiClickListener {
            println(it)
            val poiMarker = map.addMarker(
                MarkerOptions()
                    .position(it.latLng)
                    .title(it.name).snippet(it.latLng.toString())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
            )
            poiMarker?.showInfoWindow()
        }
    }

}