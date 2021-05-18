package com.example.happybirthday

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class ViewAppActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("View Activity","onCreate")
        setContentView(R.layout.view_activity_main)

        val actionBar = supportActionBar
        actionBar!!.title = "View Learn"
        actionBar.setDisplayHomeAsUpEnabled(true)

    }


    override fun onStart() {
        super.onStart()
        Log.i("View Activity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("View Activity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("View Activity","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("View Activity","onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("View Activity","onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("View Activity","onDestroy")
    }

    fun buttonPressed(v : View){
        println("button pressed from UI call")
        val btn = Toast.makeText(this,"Button Pressed", Toast.LENGTH_SHORT)
        btn.show();
        Snackbar.make(v,"Basic SnackBar",4000).setAction("Yes", View.OnClickListener {
            Toast.makeText(this,"Dismiss SnackBar", Toast.LENGTH_SHORT).show()
        }).show()
    }
    fun imagePressed(v : View){
        println("image pressed from UI call")
        val img = Toast.makeText(v.context,"Image Pressed", Toast.LENGTH_SHORT)
        img.show()
    }
    fun checkStateOfCheckBox(v: View){
        val checkBox : CheckBox = findViewById(R.id.checkbox1)
        val checkBoxState = checkBox.isChecked
        println(checkBoxState)
        Toast.makeText(v.context,"CheckBox Pressed : ${checkBoxState}", Toast.LENGTH_SHORT).show()
    }
}