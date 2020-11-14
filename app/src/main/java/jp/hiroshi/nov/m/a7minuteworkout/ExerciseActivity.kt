package jp.hiroshi.nov.m.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_exercise.*

class ExerciseActivity : AppCompatActivity() {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseTimerDuration: Long = 30

    private var exerciseList:ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar_exercise_activity)
        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_exercise_activity.setNavigationOnClickListener {
            onBackPressed() //backbutton on the screen

        }
        exerciseList = Constants.defaultExerciseList() // set Exercise list
        setupRestView()
    }

    override fun onDestroy() {

        if(restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }
        super.onDestroy()

    }

    private fun setRestProgressBar(){
        progressBar.progress = restProgress
        restTimer = object: CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                restProgress++
                progressBar.progress = 10 - restProgress
                tvTimer.text = (10 - restProgress ).toString()
            }
            override fun onFinish() {
//                Toast.makeText(
//                    this@ExerciseActivity,
//                    "Here now we will start the exercise.",
//                    Toast.LENGTH_LONG
//                ).show()
                currentExercisePosition++
                setupExerciseView()
            }
        }.start()

    }

    private fun setExerciseProgressBar(){
        progressBarExercise.progress = exerciseProgress
        exerciseTimer = object: CountDownTimer(exerciseTimerDuration * 1000,1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                progressBarExercise.progress = exerciseTimerDuration.toInt() - exerciseProgress
                tvExerciseTimer.text = (exerciseTimerDuration.toInt() - exerciseProgress ).toString()
            }
            override fun onFinish() {
//                Toast.makeText(
//                    this@ExerciseActivity,
//                    "Here now we will start the next rest screen.",
//                    Toast.LENGTH_LONG
//                ).show()
                if(currentExercisePosition < exerciseList?.size!! - 1){ // hardcodeing may cause error
                    setupRestView()
                }else{
                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congratulations! You have completed the 7 minutes workout.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }.start()
    }

    private fun setupExerciseView(){
        llRestView.visibility = View.GONE
        llExerciseView.visibility = View.VISIBLE

        if(exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        setExerciseProgressBar()

        ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tvExerciseName.text = exerciseList!![currentExercisePosition].getName()
    }


    private fun setupRestView(){
        llExerciseView.visibility = View.GONE
        llRestView.visibility = View.VISIBLE

        if(restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }
        tvUpcomingExerciseName.text = exerciseList!![currentExercisePosition +1 ].getName()

        setRestProgressBar()
    }

}
