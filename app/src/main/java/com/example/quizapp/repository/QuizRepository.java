package com.example.quizapp.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quizapp.model.QuestionList;
import com.example.quizapp.retorfit.QuestionsAPI;
import com.example.quizapp.retorfit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {

    QuestionsAPI questionsAPI;

    public QuizRepository() {
        this.questionsAPI = new RetrofitInstance()
                          .getRetrofitInstance()
                          .create(QuestionsAPI.class);
    }


    public LiveData<QuestionList> getQuestionsFromApi(){
        MutableLiveData<QuestionList> data = new MutableLiveData<>();
        Call<QuestionList> response = questionsAPI.getQuestions();
        response.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                Log.d("VIB", "onResponse: success" );
                QuestionList list = response.body();
                data.setValue(list);
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable throwable) {
                Log.d("VIB", "onResponse: failed" );
            }
        });

        return data;
    }
}
