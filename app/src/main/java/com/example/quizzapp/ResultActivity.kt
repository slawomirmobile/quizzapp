package com.example.quizzapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)



        val score = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correct = intent.getIntExtra(Constants.CORRECT_ANSWER, 0)
        val name = intent.getStringExtra(Constants.USER_NAME)
        tv_username.text = name

        tv_score.text = "Twój wynik to $correct z $score pytań"

        endbtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}