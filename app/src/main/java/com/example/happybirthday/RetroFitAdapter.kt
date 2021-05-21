package com.example.happybirthday

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RetroFitAdapter(private var data : List<Post> ) : RecyclerView.Adapter<RetroFitAdapter.ViewHolder>(){

    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        val id = v.findViewById<TextView>(R.id.id)
        val userId = v.findViewById<TextView>(R.id.userId)
        val body = v.findViewById<TextView>(R.id.body)
        val title = v.findViewById<TextView>(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ctx = parent.context
        val inflater = LayoutInflater.from(ctx)
        val view = inflater.inflate(R.layout.retro_fit_items,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = "ID -> " + data[position].id
        holder.userId.text = "USER ID -> " + data[position].userId
        holder.body.text = "BODY -> " + data[position].body
        holder.title.text = "TITLE -> " + data[position].title
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MapsActivity::class.java)
            startActivity(holder.itemView.context,intent,null)
        }
    }

    override fun getItemCount(): Int = data.size
}