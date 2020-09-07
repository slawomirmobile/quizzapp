package com.example.quizzapp

object Constants {
    //Main activity to result activity score
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWER: String = "correct_answer"



    fun getQuestions(): ArrayList<Question>  {





        val questionList = ArrayList<Question>()

        val que1 = Question(1, "Jak mam na imię", "Sławomir", "Leszek", "Maniek", 1)
        questionList.add(que1)
        val que2 = Question(2, "Ile mam lat", "25", "13", "14", 1)
        questionList.add(que2)
        val que3 = Question(3, "Imie mojego kota", "Maniek", "Mariusz", "Maksiu", 1)
        questionList.add(que3)

        return questionList

    }}