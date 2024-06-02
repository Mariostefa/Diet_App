package com.lib.calories;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class homeFragment extends Fragment {

    TextView dateTextView;
    SharedPreferences sharedPreferences;

    int sumcalor = 0;
    int sumfat =0;
    int sumprot =0;
    int sumcarbs =0;

    int calories;

    TextView bmi;
    TextView goalCalories;
    TextView dailyFats;
    TextView dailyProtein;
    TextView dailyCarbo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //sharedpreferences initialize
        sharedPreferences = getActivity().getSharedPreferences("app_profile", Context.MODE_PRIVATE);

        //ids from xml
        goalCalories = rootView.findViewById(R.id.textViewGoal);
        dailyFats = rootView.findViewById(R.id.textViewFats);
        dailyProtein = rootView.findViewById(R.id.textViewProtein);
        dailyCarbo = rootView.findViewById(R.id.textViewCarbo);
        EditText weight = rootView.findViewById(R.id.weight_goal);
        EditText calorie = rootView.findViewById(R.id.Calorie_goal);
        ConstraintLayout popup = rootView.findViewById(R.id.framelayout_pop);
        Button submit = rootView.findViewById(R.id.buttonGoal);

        // Receiver fragment's onCreateView method

        //bmi calculates
        bmi = rootView.findViewById(R.id.bmiText);
        float bmiWeight = sharedPreferences.getFloat("weight",0);
        float bmiHeight = sharedPreferences.getFloat("height",0);
        float sumBmi = bmiWeight / (bmiHeight * bmiHeight);
        if (sumBmi < 18.5 ){
            bmi.setText("BMI: " + sumBmi + " (Underweight)");
        } else if (sumBmi >=18.5 && sumBmi <24.9 ) {
            bmi.setText("BMI: " + sumBmi + " (Normal)");
        } else if (sumBmi >=24.9 && sumBmi < 29.9) {
            bmi.setText("BMI: " + sumBmi + " (Overwheight)");
        }else{
            bmi.setText("BMI: " + sumBmi + " (Obesity)");
        }

        //set the date of the day
        dateTextView = rootView.findViewById(R.id.tvDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());
        dateTextView.setText(currentDate);



        //Displays the pop-up based on some statements
        if (!sharedPreferences.getBoolean("setGoal", false)) {
            popup.setVisibility(View.VISIBLE);
            submit.setOnClickListener(v -> {
                boolean pass = popUpChecker(weight, calorie);
                if (pass == true) {
                    popup.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), "Please enter valid numbers!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            popup.setVisibility(View.GONE);
            calories = sharedPreferences.getInt("calorieGoal", 0);
            setProgress();

        }

        //gets the bundle from the planning phase
        getParentFragmentManager().setFragmentResultListener("datalist", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (result != null && result.containsKey("foodlist")) {
                    // Retrieve the HashMap from the Bundle
                    HashMap<String, ArrayList<FoodItem>> foodList = (HashMap<String, ArrayList<FoodItem>>) result.getSerializable("foodlist");
                    if (foodList != null) {
                        sumcalor = 0;
                        sumfat =0;
                        sumprot =0;
                        sumcarbs =0;
                        for (Map.Entry<String, ArrayList<FoodItem>> entry : foodList.entrySet()) {
                            String category = entry.getKey();
                            ArrayList<FoodItem> items = entry.getValue();
                            Log.d(TAG, "Category: " + category);
                            for (FoodItem item : items) {
                                Log.d(TAG, "  Name: " + item.getName());
                                sumcalor += item.getCalories();
                                sumprot += item.getProteins();
                                sumcarbs += item.getCarbohydrates();
                                sumfat += item.getFats();
                            }
                        }
                    }
                    setProgress();
                }
            }
        });

        return rootView;
    }


    //resets the values if the date has change
    public void dayReset(){
        goalCalories.setText("0 / " + calories);
        dailyFats.setText("0");
        dailyCarbo.setText("0");
        dailyProtein.setText("0");
    }


    //updates the values
    public void setProgress() {
        goalCalories.setText(String.valueOf(sumcalor) + " / " + calories);
        dailyProtein.setText(String.valueOf(sumprot));
        dailyFats.setText(String.valueOf(sumfat));
        dailyCarbo.setText(String.valueOf(sumcarbs));
    }


    //Checks the values of the pop if its filled correctly
    public boolean popUpChecker(EditText weight_goal, EditText calorie_goal ){

        SharedPreferences.Editor editor = sharedPreferences.edit();

        try{
            float weight;


            EditText weightText = weight_goal;
            String weightS = weightText.getText().toString();

            EditText caloriesText = calorie_goal;
            String caloriesS = caloriesText.getText().toString();

            weight = Float.parseFloat(weightS);
            calories = Integer.parseInt(caloriesS);

            editor.putBoolean("setGoal",true);
            editor.putFloat("weightGoal", weight);
            editor.putInt("calorieGoal", calories);
            editor.apply();

            goalCalories.setText("0 / "+ calories);
            dailyFats.setText("0");
            dailyCarbo.setText("0");
            dailyProtein.setText("0");

        }catch(NumberFormatException e){
            return false;
        }

        return true;

    }


}