package com.example.happybirthday

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DiceAppActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Dice Activity","onCreate")
        setContentView(R.layout.dice_activity_main)

        val actionBar = supportActionBar
        actionBar!!.title = "Dice Game"
        actionBar.setDisplayHomeAsUpEnabled(true)

        val btn: Button = findViewById(R.id.rollBtn)
        btn.setOnClickListener {
            Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()
            roll()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("Dice Activity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Dice Activity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Dice Activity","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Dice Activity","onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Dice Activity","onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Dice Activity","onDestroy")
    }

    private fun roll() {
        val d = Dice(6).rollDice()
        val draw = when (d) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val img: ImageView = findViewById(R.id.rollResult)
        img.setImageResource(draw)
        img.contentDescription = d.toString()
    }
}

class Dice(val sides: Int) {
    fun rollDice(): Int {
        return (1..sides).random()
    }
}