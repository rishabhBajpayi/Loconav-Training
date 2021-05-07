package com.example.happybirthday

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //checkStateOfCheckBox()
    }

    fun checkStateOfCheckBox (){
        val myButton = Button(this)
        myButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        )
        val checkBox = findViewById<View>(android.R.id.checkbox) as CheckBox
        val checkBoxState = checkBox.isChecked
        print(checkBoxState)
    }
}