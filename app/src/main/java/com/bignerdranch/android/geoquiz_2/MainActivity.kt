package com.bignerdranch.android.geoquiz_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var previousButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var cheatButton: Button

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quizViewModel.currentIndex =
            savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0

        questionTextView = findViewById(R.id.question_text_view)
        questionTextView.setOnClickListener {
            quizViewModel.moveToNextQuestion()
            updateQuestion()
        }

        trueButton = findViewById(R.id.true_button)
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton = findViewById(R.id.false_button)
        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        previousButton = findViewById(R.id.prev_button)
        previousButton.setOnClickListener {
            quizViewModel.moveToPreviousQuestion()
            updateQuestion()
        }

        nextButton = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            quizViewModel.moveToNextQuestion()
            updateQuestion()
        }

        cheatButton = findViewById(R.id.cheat_button)
        cheatButton.setOnClickListener {
            startActivity(Intent(this, CheatActivity::class.java))
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val resultStrId = when {
            quizViewModel.isCheater -> R.string.cheat_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.wrong_toast
        }

        Toast.makeText(this, resultStrId, Toast.LENGTH_SHORT).show()
    }

    private fun updateQuestion() {
        questionTextView.setText(quizViewModel.currentQuestionText)
    }
}
