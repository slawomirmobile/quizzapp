package com.example.quizzapp

import android.content.Intent
import android.graphics.Color
import android.graphics.ImageFormat
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionLists: ArrayList<Question>? = null
    private var mSelectedPosition: Int = 0
    private var mCorrectAnswer: Int = 0
    private var mUsername: String?  = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mQuestionLists = Constants.getQuestions()
        mUsername = intent.getStringExtra(Constants.USER_NAME)
        setQuestion()
        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

    }

    private fun setQuestion() {
        //Questions
       // mCurrentPosition = 1

        val question = mQuestionLists!!.get(mCurrentPosition - 1)
        defaultOptionView()

        if (mCurrentPosition == mQuestionLists!!.size) {
            btn_submit.text = "Zakończ test"
        } else {
            btn_submit.text = "Następne pytanie"
        }

        progressBar.progress = mCurrentPosition

        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max
        tv_question.text = question!!.question

        //Pytania ustawienie
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
    }


    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0, tvOptionOne)
        options.add(1, tvOptionTwo)
        options.add(2, tvOptionThree)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background =
                ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvOptionOne -> {
                selectedOptionView(tvOptionOne, 1)
            }
            R.id.tvOptionTwo -> {
                selectedOptionView(tvOptionTwo, 2)
            }
            R.id.tvOptionThree -> {
                selectedOptionView(tvOptionThree, 3)
            }
            R.id.btn_submit -> {

                if (mSelectedPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionLists!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUsername)
                            intent.putExtra(Constants.CORRECT_ANSWER, mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionLists!!.size)
                            startActivity(intent)
                        }
                    }
                } else {


                    val question = mQuestionLists?.get(mCurrentPosition - 1)

                    if (question!!.correctanswer != mSelectedPosition) {
                        answerView(mSelectedPosition, R.drawable.incorrect_option_bg)
                    } else {
                        mCorrectAnswer++
                    }
                    answerView(question.correctanswer, R.drawable.correct_option_bg)

                    if (mCurrentPosition == mQuestionLists!!.size) {
                        btn_submit.text = "Zakoncz"
                    } else {
                        btn_submit.text = "Następne pytanie"
                    }
                    mSelectedPosition = 0
                }
            }
        }
    }
    private fun answerView(answer: Int, drawableView: Int) {
        when(answer) {
            1 -> {
                tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }


    private fun selectedOptionView(tv: TextView, selecteditems: Int) {
        defaultOptionView()
        mSelectedPosition = selecteditems

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }
}