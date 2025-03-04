package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.quizapp.databinding.ActivityResultsBinding;


public class ResultsActivity extends AppCompatActivity {

    ActivityResultsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_results);

        binding.txtAnswer.setText(
                "Your score is: "
                        +MainActivity.result
                        +"/" +MainActivity.totalQuestions);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultsActivity.this
                        , MainActivity.class);
                startActivity(intent);
            }
        });


    }
}