package com.example.happybirthday

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        setContentView(R.layout.hb_activity_main)
        setContentView(R.layout.dice_activity_main)
        Toast.makeText(applicationContext, "Application is Started", Toast.LENGTH_SHORT).show()
        val btn: Button = findViewById(R.id.rollBtn)
        btn.setOnClickListener {
            Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()
            roll()
        }
//        val txt = findViewById<TextView>(R.id.message)
//        txt.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"))
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

//    fun buttonPressed(v :View){
//        println("button pressed from UI call")
//        val btn = Toast.makeText(applicationContext,"Button Pressed",Toast.LENGTH_SHORT)
//        btn.show();
//        Snackbar.make(v,"Basic SnackBar",4000).setAction("Yes",View.OnClickListener {
//            Toast.makeText(applicationContext,"Dismiss SnackBar",Toast.LENGTH_SHORT).show()
//        }).show()
//    }
//    fun imagePressed(v :View){
//        println("image pressed from UI call")
//        val img = Toast.makeText(applicationContext,"Image Pressed",Toast.LENGTH_SHORT)
//        img.show()
//    }
//    fun checkStateOfCheckBox (){
//        val checkBox = findViewById<View>(android.R.id.checkbox) as CheckBox
//        val checkBoxState = checkBox.isChecked
//        print(checkBoxState)
//    }
}

class Dice(val sides: Int) {
    fun rollDice(): Int {
        return (1..sides).random()
    }
}