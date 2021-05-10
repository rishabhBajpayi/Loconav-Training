package com.example.happybirthday

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hb_activity_main)
        Toast.makeText(applicationContext,"Application is Started",Toast.LENGTH_SHORT).show()
//        val txt = findViewById<TextView>(R.id.message)
//        txt.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"))
    }

    fun buttonPressed(v :View){
        println("button pressed from UI call")
        val btn = Toast.makeText(applicationContext,"Button Pressed",Toast.LENGTH_SHORT)
        btn.show();
        Snackbar.make(v,"Basic SnackBar",4000).setAction("Yes",View.OnClickListener {
            Toast.makeText(applicationContext,"Dismiss SnackBar",Toast.LENGTH_SHORT).show()
        }).show()
    }
    fun imagePressed(v :View){
        println("image pressed from UI call")
        val img = Toast.makeText(applicationContext,"Image Pressed",Toast.LENGTH_SHORT)
        img.show()
    }
    fun checkStateOfCheckBox (){
        val checkBox = findViewById<View>(android.R.id.checkbox) as CheckBox
        val checkBoxState = checkBox.isChecked
        print(checkBoxState)
    }
}