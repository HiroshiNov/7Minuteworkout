package jp.hiroshi.nov.m.a7minuteworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.dialog_custom_back_confirmation.*
import java.util.*
import kotlin.collections.ArrayList

/** Handles workout timer sequence and exercise UI. */
class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    /** Countdown during rest periods. */
    private var restTimer: CountDownTimer? = null
    /** Progress counter for the rest timer. */
    private var restProgress = 0
    /** Duration of each rest period in seconds. */
    private var restTimerDuration: Long = 10
    /** Countdown during an exercise. */
    private var exerciseTimer: CountDownTimer? = null
    /** Progress counter for the exercise timer. */
    private var exerciseProgress = 0

    /** Duration of each exercise in seconds. */
    private var exerciseTimerDuration: Long = 30

    /** List of exercises to perform. */
    private var exerciseList: ArrayList<ExerciseModel>? = null
    /** Index of the currently displayed exercise. */
    private var currentExercisePosition = -1

    /** Text-to-speech engine for exercise names. */
    private var tts: TextToSpeech? = null

    /** Plays audio cues between exercises. */
    private var player: MediaPlayer? = null

    /** Adapter for the recycler showing exercise status. */
    private var exerciseAdapter: ExerciseStatusAdapter? = null

    /** Sets up views and timers when the activity is created. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar_exercise_activity)
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_exercise_activity.setNavigationOnClickListener {
            customDialogForBackButton()
            //            onBackPressed() //backbutton on the screen

        }

        tts = TextToSpeech(this, this)

        exerciseList = Constants.defaultExerciseList() // set Exercise list
        setupRestView()

        setupExerciseStatusRecyclerView()



    }

    /** Cleans up timers, TTS, and media on destroy. */
    override fun onDestroy() {

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        if(tts!= null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if(player!= null){
            player!!.stop()
        }

        super.onDestroy()



    }

    /** Starts and updates the rest countdown bar. */
    private fun setRestProgressBar() {
        progressBar.progress = restProgress
        restTimer = object : CountDownTimer(restTimerDuration * 1000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                progressBar.progress = restTimerDuration.toInt() - restProgress
                tvTimer.text = (restTimerDuration.toInt() - restProgress).toString()
            }

            override fun onFinish() {
//                Toast.makeText(
//                    this@ExerciseActivity,
//                    "Here now we will start the exercise.",
//                    Toast.LENGTH_LONG
//                ).show()
                currentExercisePosition++

                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()


                setupExerciseView()
            }
        }.start()

    }

    /** Starts and updates the exercise countdown bar. */
    private fun setExerciseProgressBar() {
        progressBarExercise.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(exerciseTimerDuration * 1000, 1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                progressBarExercise.progress = exerciseTimerDuration.toInt() - exerciseProgress
                tvExerciseTimer.text = (exerciseTimerDuration.toInt() - exerciseProgress).toString()
            }

            override fun onFinish() {
//                Toast.makeText(
//                    this@ExerciseActivity,
//                    "Here now we will start the next rest screen.",
//                    Toast.LENGTH_LONG
//                ).show()
                 if (currentExercisePosition < exerciseList?.size!! - 1) {
                     // hardcoding may cause error
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()

                    setupRestView()
                } else {
//                    Toast.makeText(
//                        this@ExerciseActivity,
//                        "Congratulations! You have completed the 7 minutes workout.",
//                        Toast.LENGTH_LONG
//                    ).show()
                    finish()
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    /** Displays the exercise view and begins the timer. */
    private fun setupExerciseView() {
        llRestView.visibility = View.GONE
        llExerciseView.visibility = View.VISIBLE

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())

        setExerciseProgressBar()

        ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tvExerciseName.text = exerciseList!![currentExercisePosition].getName()
    }


    /** Shows the rest view between exercises. */
    private fun setupRestView() {
        try{
            //val soundURI = Uri.parse("android:resource://")
            player = MediaPlayer.create(applicationContext, R.raw.press_start)

            player!!.isLooping = false
            player!!.start()

        }catch(e:Exception){
             e.printStackTrace()
        }



        llExerciseView.visibility = View.GONE
        llRestView.visibility = View.VISIBLE

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        tvUpcomingExerciseName.text = exerciseList!![currentExercisePosition + 1].getName()

        setRestProgressBar()
    }

    /** Callback for TextToSpeech initialization. */
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    /** Speaks the provided text using TextToSpeech. */
    private fun speakOut(text: String) {
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH, null, "")
    }

    /** Configures the RecyclerView that shows exercise status. */
    private fun setupExerciseStatusRecyclerView(){
        rvExerciseStatus.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!,this)
        rvExerciseStatus.adapter = exerciseAdapter
    }

    /** Shows a confirmation dialog when the back button is pressed. */
    private fun customDialogForBackButton(){
        val customDialog = Dialog(this)

        customDialog.setContentView(R.layout.dialog_custom_back_confirmation)
        customDialog.tvYes.setOnClickListener{
            finish()
            customDialog.dismiss()
        }
        customDialog.tvNo.setOnClickListener{
            customDialog.dismiss()
        }
        customDialog.show()
    }
}
