package com.example.mapproject.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mapproject.MapsActivity
import com.example.mapproject.databinding.VDetailsComponentBinding
import com.example.mapproject.model.VDetails

class VDetailsAdapter(private val data: List<VDetails>) :
    RecyclerView.Adapter<VDetailsAdapter.ViewHolder>() {

    lateinit var bind: VDetailsComponentBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var ctx = parent.context
        val inf = LayoutInflater.from(ctx)
        bind = VDetailsComponentBinding.inflate(inf,parent,false)
        return ViewHolder(bind)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position)
    }

    override fun getItemCount(): Int = data.size


    inner class ViewHolder(val binding : VDetailsComponentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(position: Int) {
            binding.id.text = data[position].id.toString()
            binding.displayNumber.text = data[position].display_number
            binding.iconKind.text = data[position].icon_kind
            binding.vehicleMake.text = data[position].vehicle_make
            binding.vehicleModel.text = data[position].vehicle_model
            binding.constView.setOnClickListener {
                onClickView(position)
            }
        }

        fun onClickView(position: Int){
            if (data[position].last_location.lat != null && data[position].last_location.long != null){
                val intent = Intent(binding.constView.context, MapsActivity::class.java)
                intent.putExtra(
                    "lastLoc",
                    doubleArrayOf(
                        data[position].last_location.lat!!,
                        data[position].last_location.long!!,
                        data[position].last_location.speed!!,
                        )
                )
                intent.putExtra("display",data[position].display_number)
                ContextCompat.startActivity(binding.constView.context, intent, null)
            } else{
                Toast.makeText(binding.constView.context, "No Data Available", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}