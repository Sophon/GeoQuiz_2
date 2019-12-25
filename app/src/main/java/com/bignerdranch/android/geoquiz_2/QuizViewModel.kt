package com.bignerdranch.android.geoquiz_2

import androidx.lifecycle.ViewModel

private const val MAX_NO_CHEATS = 2

class QuizViewModel: ViewModel() {
    val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    var currentIndex = 0
    private var cheatCount = 0
    var isCheater: Boolean
        get() = questionBank[currentIndex].hasCheated
        set(value) {
            questionBank[currentIndex].hasCheated = value
        }
    var cheatingEnabled = true

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToPreviousQuestion() {
        if(currentIndex != 0) {
            currentIndex -= 1
        }
    }

    fun moveToNextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun userCheated() {
        cheatCount++
        if(cheatCount >= MAX_NO_CHEATS) cheatingEnabled = false
    }
}