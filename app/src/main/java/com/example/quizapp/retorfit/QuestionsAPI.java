package com.example.quizapp.retorfit;

import com.example.quizapp.model.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionsAPI {



    @GET("my_quiz_db.php")
    Call<QuestionList> getQuestions();

}
