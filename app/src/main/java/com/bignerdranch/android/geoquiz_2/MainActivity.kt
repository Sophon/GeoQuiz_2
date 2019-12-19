package com.bignerdranch.android.geoquiz_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var cheatButton: Button

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        trueButton.setOnClickListener {
            //TODO: check answer
        }

        falseButton = findViewById(R.id.false_button)
        falseButton.setOnClickListener {
            //TODO: check answer
        }

        previousButton = findViewById(R.id.prev_button)
        previousButton.setOnClickListener {
            //TODO: previous question
        }

        nextButton = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            //TODO: next question
        }

        cheatButton = findViewById(R.id.cheat_button)
        cheatButton.setOnClickListener {
            //TODO: cheat
        }
    }
}
