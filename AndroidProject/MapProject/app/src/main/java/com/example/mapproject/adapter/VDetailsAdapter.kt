package com.example.mapproject.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mapproject.MapsActivity
import com.example.mapproject.databinding.VDetailsComponentBinding
import com.example.mapproject.model.VDetails

class VDetailsAdapter(private val data: List<VDetails>) :
    RecyclerView.Adapter<VDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(VDetailsComponentBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val binding: VDetailsComponentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(v_details: VDetails) {
            v_details.let {
                binding.id.text = it.id.toString()
                binding.displayNumber.text = it.display_number
                binding.iconKind.text = it.icon_kind
                binding.vehicleMake.text = it.vehicle_make
                binding.vehicleModel.text = it.vehicle_model
                binding.constView.setOnClickListener { _ ->
                    onClickView(it)
                }
            }
        }

        private fun onClickView(v_details: VDetails) {
            v_details.last_location.lat?.let {
                val intent = Intent(binding.constView.context, MapsActivity::class.java)
                intent.putExtra(
                    "lastLoc",
                    doubleArrayOf(
                        it,
                        v_details.last_location.long ?: 0.0,
                    )
                )
                intent.putExtra("speed",v_details.last_location.speed)
                intent.putExtra("display", v_details.display_number)
                binding.constView.context.startActivity(intent)
            } ?: Toast.makeText(binding.constView.context, "No Data Available", Toast.LENGTH_SHORT)
                .show()
        }
    }
}