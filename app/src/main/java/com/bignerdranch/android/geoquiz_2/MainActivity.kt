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
    }
}
