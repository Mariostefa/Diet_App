package com.lib.calories;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import org.jetbrains.annotations.Nullable;


public final class setUpActivity extends ComponentActivity {

    private SharedPreferences sharedPreferences;
    Button button;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_layout);

        button = findViewById(R.id.submit_button);
        sharedPreferences = getSharedPreferences("app_profile", Context.MODE_PRIVATE);

        //checks if its the first time the app is launched
        if (!firstTime()){
            Intent intent = new Intent(getApplicationContext(), mainActivity.class);
            startActivity(intent);
            finish();
        }


        //when the button is pressed checks if the values are filled correctly or not.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mainActivity.class);

                if (setUp()){
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Please fill all the gaps CORRECTLY !", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    //returns the setUp values
    private Boolean firstTime(){
        return sharedPreferences.getBoolean("setUp",true);
    }


    // gets and pass the values on the sharedpreferebce if they are filled correctly
    private Boolean setUp(){

        SharedPreferences.Editor editor = sharedPreferences.edit();

        // GETS THE VALUE FROM THE SPECIFIED EDIT TEXTS AND CONVERTS THE TO STRING.
        EditText weightInput = findViewById(R.id.InputWeightStart);
        String weightChoice = weightInput.getText().toString();

        EditText HeightInput = findViewById(R.id.InputHeightStart);
        String heightChoice = HeightInput.getText().toString();

        EditText AgeInput = findViewById(R.id.InputAge);
        String ageChoice = AgeInput.getText().toString();

        Spinner sexInput = findViewById(R.id.Sex);
        String sexChoice = String.valueOf(sexInput.getSelectedItem());

        float weight;
        float height;
        int age;
        int sex;


        // TAKES THE STRINGS AND CONVERTS THEM
        try {
            weight = Float.parseFloat(weightChoice);
            height = Float.parseFloat(heightChoice);
            age = Integer.parseInt(ageChoice);

            switch(sexChoice) {
                case "Female":
                    sex = 1;
                    break;
                case "other":
                    sex = 2;
                    break;
                default:
                    sex = 0;

            }

            //VALUES FOR PROFILE
            editor.putBoolean("setUp",false);
            editor.putFloat("weight", weight);
            editor.putFloat("height", height);
            editor.putInt("age", age);
            editor.putInt("Sex", sex);
            editor.putBoolean("goal",true);
            editor.putBoolean("setGoal",false);
            editor.apply();

        }catch(NumberFormatException e){
            return false;
        }

        return true;
    }
}
