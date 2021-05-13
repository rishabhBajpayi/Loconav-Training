package com.example.happybirthday

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.happybirthday.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Main Activity","Main Activity onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar!!.title = "Multi App Screen"
        Toast.makeText(this, "Main Activity Started", Toast.LENGTH_SHORT).show()
        binding.HbAppBtn.setOnClickListener {
            val intent = Intent(this, HbAppActivity::class.java)
            startActivity(intent)
        }
        binding.DiceAppBtn.setOnClickListener {
            val intent = Intent(this, DiceAppActivity::class.java)
            startActivity(intent)
        }
        binding.ViewAppBtn.setOnClickListener {
            val intent = Intent(this, ViewAppActivity::class.java)
            startActivity(intent)
        }
        binding.TipAppBtn.setOnClickListener {
            val intent = Intent(this, TipAppActivity::class.java)
            startActivity(intent)
        }
//        val txt = findViewById<TextView>(R.id.message)
//        txt.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"))
    }

    override fun onStart() {
        super.onStart()
        Log.i("Main Activity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Main Activity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Main Activity","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Main Activity","onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Main Activity","onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Main Activity","onDestroy")
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}
