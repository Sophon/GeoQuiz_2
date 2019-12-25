package com.bignerdranch.android.geoquiz_2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"

private const val TAG = "CheatActivity"
private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {
    private lateinit var showAnswerButton: Button
    private lateinit var answerTextView: TextView
    private lateinit var versionTextView: TextView

    private val cheatViewModel: CheatViewModel by lazy {
        ViewModelProviders.of(this).get(CheatViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        cheatViewModel.answer =
            intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        answerTextView = findViewById(R.id.answer_text_view)
        answerTextView.setText(cheatViewModel.answerText)

        showAnswerButton = findViewById(R.id.show_answer_button)
        showAnswerButton.setOnClickListener {
            revealAnswer()
            setAnswerShown()
        }

        versionTextView = findViewById(R.id.version_text_view)
        versionTextView.text = getString(R.string.api_text, Build.VERSION.SDK_INT)

        setResult(Activity.RESULT_OK, cheatViewModel.returnIntent)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }

    private fun revealAnswer() {
        cheatViewModel.answerText =
            when(cheatViewModel.answer) {
                true -> R.string.true_button
                else -> R.string.false_button
            }
        answerTextView.setText(cheatViewModel.answerText)
        cheatViewModel.hasCheated = true
    }

    private fun setAnswerShown() {
        cheatViewModel.returnIntent.apply {
            putExtra(EXTRA_ANSWER_SHOWN, cheatViewModel.hasCheated)
        }
    }
}
