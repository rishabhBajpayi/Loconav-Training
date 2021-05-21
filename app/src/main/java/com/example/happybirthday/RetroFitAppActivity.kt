package com.example.happybirthday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetroFitAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.retro_fit_main_activity)
        val actionBar = supportActionBar
        actionBar!!.title = "RetroFit App"
        actionBar.setDisplayHomeAsUpEnabled(true)

        val serviceGenerator = ServiceGenerator.buildService(JsonPlaceHolderApi::class.java)
        val call = serviceGenerator.getPosts()
        call.enqueue(object : Callback<MutableList<Post>> {

            override fun onFailure(call: Call<MutableList<Post>>, t: Throwable) {
                t.printStackTrace()
                println(t.message.toString())
            }

            override fun onResponse(call: Call<MutableList<Post>>, response: Response<MutableList<Post>>) {
                if (response.isSuccessful) {
                    val view = findViewById<RecyclerView>(R.id.recycler_view)
                    val adapter = RetroFitAdapter(response.body()!!)
                    view.adapter = adapter
                    view.layoutManager = LinearLayoutManager(baseContext)
                }else{
                    print(response.message())
                }
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else super.onOptionsItemSelected(item)
    }
}