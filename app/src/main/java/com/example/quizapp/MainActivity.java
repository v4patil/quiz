package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

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
        setContentView(R.layout.activity_main);

        // Data Binding
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        // Resetting the Scores:
        result = 0;
        totalQuestions = 0;

        // Creating an instance of 'QuizViewModel'
        quizViewModel = new ViewModelProvider(this)
                .get(QuizViewModel.class);

        // Displaying the First Question:
        DisplayFirstQuestion();

        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayNextQuestions();
            }
        });


    }

    public void DisplayNextQuestions(){

        // Direct the user to the Results activity
        if (binding.buttonNext.getText().equals("Finish")){
            Intent i = new Intent(MainActivity.this, ResultsActivity.class);
            startActivity(i);
            finish();
        }


        // Displaying the question
        int selectedOption = binding.radioGroup.getCheckedRadioButtonId();
        if (selectedOption != -1){
            RadioButton radioButton = findViewById(selectedOption);

            // More Questions to Display??
            if ((questionList.size() -i) > 0){

                // Getting the number of questions
                totalQuestions = questionList.size();

                // Check if the chosen option is correct
                if(radioButton.getText().toString().equals(
                        questionList.get(i).getCorrectOption()
                )){
                    result++;
                    binding.txtResult.setText(
                            "Correct Answers: "+result
                    );
                }

                if (i ==0){
                    i++;
                }

                // Displaying the next Questions
                binding.txtQuestion.setText("Question "+(i+1)+ " : "+
                        questionList.get(i).getQuestion());

                binding.radio1.setText(questionList.get(i).getOption1());
                binding.radio2.setText(questionList.get(i).getOption2());
                binding.radio3.setText(questionList.get(i).getOption3());
                binding.radio4.setText(questionList.get(i).getOption4());


                // Check if it is the last question
                if(i == (questionList.size() -1)){
                    binding.buttonNext.setText("Finish");
                }

                binding.radioGroup.clearCheck();
                i++;


            }else{
                if (radioButton.getText().toString().equals(
                        questionList.get(i-1).getCorrectOption()
                )){
                    result++;
                    binding.txtResult.setText("Correct Answers : "+result);
                }

            }
        }else{
            Toast.makeText(
                    this,
                    "You need to make a selection",
                    Toast.LENGTH_SHORT).show();
        }

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