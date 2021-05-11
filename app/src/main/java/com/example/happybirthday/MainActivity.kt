package com.example.happybirthday

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.happybirthday.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.view_activity_main)
//        setContentView(R.layout.hb_activity_main)
//        setContentView(R.layout.dice_activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(applicationContext, "Application is Started", Toast.LENGTH_SHORT).show()
        binding.clcTip.setOnClickListener {
            calculateTip()
        }

//    --------button used in dice UI--------
//        val btn: Button = findViewById(R.id.rollBtn)
//        btn.setOnClickListener {
//            Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()
//            roll()
//        }


//        val txt = findViewById<TextView>(R.id.message)
//        txt.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"))
    }

    fun calculateTip(){
        val costInStr = binding.costTxt.text.toString()
        val cost = costInStr.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = getString(R.string.tip_amount, "")
            return
        }
        val isSelect = binding.radGrp.checkedRadioButtonId
        var tip = when(isSelect){
            R.id.rd20 -> cost*0.2
            R.id.rd18 -> cost*0.18
            else -> cost*0.15
        }
        if(binding.swch.isChecked)
            tip = kotlin.math.ceil(tip)

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

//    -------------function used in Dice UI---------
//    private fun roll() {
//        val d = Dice(6).rollDice()
//        val draw = when (d) {
//            1 -> R.drawable.dice_1
//            2 -> R.drawable.dice_2
//            3 -> R.drawable.dice_3
//            4 -> R.drawable.dice_4
//            5 -> R.drawable.dice_5
//            else -> R.drawable.dice_6
//        }
//        val img: ImageView = findViewById(R.id.rollResult)
//        img.setImageResource(draw)
//        img.contentDescription = d.toString()
//    }


//    ---------------buttons used in Views UI------------------
//    fun buttonPressed(v :View){
//        println("button pressed from UI call")
//        val btn = Toast.makeText(applicationContext,"Button Pressed",Toast.LENGTH_SHORT)
//        btn.show();
//        Snackbar.make(v,"Basic SnackBar",4000).setAction("Yes",View.OnClickListener {
//            Toast.makeText(this,"Dismiss SnackBar",Toast.LENGTH_SHORT).show()
//        }).show()
//    }
//    fun imagePressed(v :View){
//        println("image pressed from UI call")
//        val img = Toast.makeText(this,"Image Pressed",Toast.LENGTH_SHORT)
//        img.show()
//    }


//    fun checkStateOfCheckBox (){
//        val checkBox = findViewById<View>(android.R.id.checkbox) as CheckBox
//        val checkBoxState = checkBox.isChecked
//        print(checkBoxState)
//    }
}

// ---------class for dice Functioning-----
//class Dice(val sides: Int) {
//    fun rollDice(): Int {
//        return (1..sides).random()
//    }
//}