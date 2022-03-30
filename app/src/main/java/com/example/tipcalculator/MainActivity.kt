package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var amount: Double = 0.00
    var tipPercent: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTipPercent.text = "${tipSeekBar.progress}%"
        tipPercent = tipSeekBar.progress

        tipSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tipPercent = p1

                txtTipPercent.text = "$p1%"

                setTipValue()

                setTotalValue()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        edtAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val value: Double? = p0.toString().toDoubleOrNull()

                if (value != null) {
                    amount = value

                    setTipValue()

                    setTotalValue()
                } else {
                    Toast.makeText(this@MainActivity, "Empty Amount", Toast.LENGTH_SHORT).show()
                    resetValue()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        swNightMode.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                if (p1) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        })
    }

    fun setTipValue() {
        txtValueTip.text = String.format("%.2f", amount * tipPercent / 100.00)
    }

    fun setTotalValue() {
        txtTotalValue.text =
            String.format("%.2f", ((amount * tipPercent / 100.00)) + amount)
    }

    fun resetValue() {
        txtValueTip.text = "Tip"
        txtTotalValue.text = "Total"
    }
}
