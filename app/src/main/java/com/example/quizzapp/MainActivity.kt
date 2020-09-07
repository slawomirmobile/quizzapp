package com.example.quizzapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


     btn_start.setOnClickListener {
             val intent = Intent(this, QuizQuestionsActivity::class.java)
             intent.putExtra(Constants.USER_NAME, et_name_text.text.toString())
             startActivity(intent)
             finish()

     }


    }
}