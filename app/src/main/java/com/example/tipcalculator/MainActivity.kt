package com.example.tipcalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.tipcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var amount: Double = 0.00
    private var tipPercent: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.txtTipPercent?.text = "${binding?.tipSeekBar?.progress}%"
        tipPercent = binding!!.tipSeekBar.progress

        binding?.tipSeekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tipPercent = p1

                binding?.txtTipPercent?.text = "$p1%"

                setTipValue()

                setTotalValue()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

        binding?.edtAmount?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

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

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding?.swNightMode?.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
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
        binding?.txtValueTip?.text = String.format("%.2f", amount * tipPercent / 100.00)
    }

    fun setTotalValue() {
        binding?.txtTotalValue?.text =
            String.format("%.2f", ((amount * tipPercent / 100.00)) + amount)
    }

    fun resetValue() {
        binding?.txtValueTip?.text = "Tip"
        binding?.txtTotalValue?.text = "Total"
        amount = 0.0
    }
}
