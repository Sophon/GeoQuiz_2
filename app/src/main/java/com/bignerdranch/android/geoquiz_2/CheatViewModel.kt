package com.bignerdranch.android.geoquiz_2

import android.content.Intent
import androidx.lifecycle.ViewModel

class CheatViewModel: ViewModel() {
    var hasCheated = false
    var answer = false
    var answerText = R.string.default_answer_text
    var returnIntent = Intent()
}