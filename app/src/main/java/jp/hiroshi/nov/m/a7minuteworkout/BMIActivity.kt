package jp.hiroshi.nov.m.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_b_m_i.*
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_i)

        setSupportActionBar(toolbar_bmi_activity)
        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "CALCULATE BMI"

        }
        toolbar_bmi_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btnCalculateUnits.setOnClickListener {
            if(validateMetricUnits()){
                val heightValue : Float = etMetricUnitHeight.text.toString().toFloat() /100
                val weightValue : Float = etMetricUnitWeight.text.toString().toFloat()

                val bmi = weightValue / (heightValue*heightValue)
                displayBMIResult(bmi)
            }else{
                Toast.makeText(this,"",Toast.LENGTH_LONG).show()
            }


        }

    }

    private fun displayBMIResult(bmi : Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Too thin"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Too thin"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Too thin"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "You're nomal"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Danger"
            bmiDescription = "Too fat"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Danger"
            bmiDescription = "Too fat"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Danger"
            bmiDescription = "Too fat"
        } else {
            bmiLabel = "Unknown"
            bmiDescription = "Unknown"
        }

        tvYourBMI.visibility = View.VISIBLE
        tvBMIValue.visibility = View.VISIBLE
        tvBMIType.visibility = View.VISIBLE
        tvBMIDescription.visibility = View.VISIBLE

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        tvBMIValue.text = bmiValue
        tvBMIType.text = bmiLabel
        tvBMIDescription.text = bmiDescription

    }



    private fun validateMetricUnits():Boolean {
        var isValid = true

        if (etMetricUnitWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (etMetricUnitHeight.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }
}
