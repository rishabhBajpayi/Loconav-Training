package com.example.happybirthday

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.happybirthday.databinding.TipActivityMainBinding
import java.text.NumberFormat


class TipAppActivity : AppCompatActivity() {
    lateinit var binding : TipActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TipActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar!!.title = "Tip Calculator"
        actionBar.setDisplayHomeAsUpEnabled(true)

        Toast.makeText(this, "Application is Started", Toast.LENGTH_SHORT).show()
        binding.clcTip.setOnClickListener {
            calculateTip()
        }
        binding.costEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    fun calculateTip(){
        val costInStr = binding.costEditText.text.toString()
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
