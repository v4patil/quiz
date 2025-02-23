package com.example.quizapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.quizapp.databinding.ActivityMainBinding;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuestionList;
import com.example.quizapp.viewmodel.QuizViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private QuizViewModel quizViewModel;
    private List<Question> questionList;

    static int result = 0;
    static int totalQuestions = 0;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        result = 0;
        totalQuestions = 0;

        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        DisplayFirstQuestion();
    }

    private void DisplayFirstQuestion() {
        quizViewModel.getQuestionListLiveData().observe(this, new Observer<QuestionList>() {
            @Override
            public void onChanged(QuestionList questions) {
                questionList = questions;

                binding.txtQuestion.setText("Question 1: " + questions.get(0).getQuestion());
                binding.radio1.setText("Question 1: " + questions.get(0).getOption1());
                binding.radio2.setText("Question 1: " + questions.get(0).getOption2());
                binding.radio3.setText("Question 1: " + questions.get(0).getOption3());
                binding.radio4.setText("Question 1: " + questions.get(0).getOption4());
            }
        });
    }
}