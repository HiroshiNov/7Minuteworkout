package jp.hiroshi.nov.m.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llStart.setOnClickListener {
//            Toast.makeText(this, "Here we wil start the exercize",Toast.LENGTH_LONG).show()
        val intent = Intent(this, ExerciseActivity::class.java) //::class menase
            startActivity(intent)

        }


        llBMI.setOnClickListener {
            val intent = Intent(this, BMIActivity::class.java) //::class menase
            startActivity(intent)

        }
    }

}
