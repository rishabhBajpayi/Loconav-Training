package com.example.happybirthday

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HbAppActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("HB Activity","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hb_activity_main)
        val actionBar = supportActionBar
        actionBar!!.title = "Birthday Greeting"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        Log.i("HB Activity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("HB Activity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("HB Activity","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("HB Activity","onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("HB Activity","onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("HB Activity","onDestroy")
    }
}