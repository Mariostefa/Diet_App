package com.lib.calories;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class programFragment extends Fragment {

    SharedPreferences sharedPreferences;

    public HashMap<String, List<FoodItem>> selectedFoods;
    private int totalCalories = 0;
    private int calorieGoal;
    private TextView tvCalories;
    private HashMap<String, FoodItem> allFoods;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retain this fragment across configuration changes
        sharedPreferences = getActivity().getSharedPreferences("app_profile", Context.MODE_PRIVATE);
        calorieGoal = sharedPreferences.getInt("calorieGoal", 0);
        System.out.println(calorieGoal);

        selectedFoods = new HashMap<>();
        selectedFoods.put("Breakfast", new ArrayList<>());
        selectedFoods.put("Tithe", new ArrayList<>());
        selectedFoods.put("Lunch", new ArrayList<>());
        selectedFoods.put("Afternoon", new ArrayList<>());
        selectedFoods.put("Dinner", new ArrayList<>());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_plan, container, false);

        tvCalories = rootView.findViewById(R.id.tvCalories);
        tvCalories.setText(totalCalories + "/" + calorieGoal);

        // Setup buttons
        setupButton(rootView,R.id.btnBreakfast, "Breakfast");
        setupButton(rootView,R.id.btnTithe, "Tithe");
        setupButton(rootView,R.id.btnLunch, "Lunch");
        setupButton(rootView,R.id.btnAfternoon, "Afternoon");
        setupButton(rootView,R.id.btnDinner, "Dinner");

        // Setup show all selected foods button
        setupShowSelectedFoodsButton(rootView,R.id.btnShowSelectedFoods);

        allFoods = new HashMap<>();
        allFoods.put("Banana", new FoodItem("Banana", 105, 1, 27,1));
        allFoods.put("Bread", new FoodItem("Bread", 80, 3, 15,2));
        allFoods.put("Cheese", new FoodItem("Cheese", 113, 7, 1,3));
        allFoods.put("Chicken Breast (100g)", new FoodItem("Chicken Breast (100g)", 165, 31, 0,4));
        allFoods.put("Broccoli (1 cup, chopped)", new FoodItem("Broccoli (1 cup, chopped)", 55, 4, 11,5));
        allFoods.put("Brown Rice (1 cup cooked)", new FoodItem("Brown Rice (1 cup cooked)", 215, 5, 45,6));
        allFoods.put("Salmon (100g)", new FoodItem( "Salmon (100g)", 206, 22, 0,7));
        allFoods.put("Almonds (1 oz)", new FoodItem("Almonds (1 oz)", 164, 6, 6,8));
        allFoods.put("Orange", new FoodItem("Orange", 62, 1, 15,9));
        allFoods.put("Sweet Potato (1 medium)", new FoodItem("Sweet Potato (1 medium)", 103, 2, 24,10));
        allFoods.put("Greek Yogurt (1 cup)", new FoodItem("Greek Yogurt (1 cup)", 100, 10, 6,11));
        allFoods.put("Oats (1 cup cooked)", new FoodItem("Oats (1 cup cooked)", 154, 6, 27,12));
        allFoods.put("Egg (1 large", new FoodItem("Egg (1 large)", 70, 6, 1,13));
        allFoods.put("Quinoa (1 cup cooked)", new FoodItem("Quinoa (1 cup cooked)", 222, 8, 39,14));
        allFoods.put("Spinach (1 cup cooked)", new FoodItem("Spinach (1 cup cooked)", 41, 5, 7,15));
        allFoods.put("Apple", new FoodItem("Apple", 95, 0, 25,16));
        allFoods.put("Avocado (1 medium)", new FoodItem("Avocado (1 medium)", 234, 3, 12,17));
        allFoods.put("Blueberries (1 cup)", new FoodItem("Blueberries (1 cup)", 84, 1, 21,18));
        allFoods.put("Tofu (100g)", new FoodItem("Tofu (100g)", 76, 8, 2,19));
        allFoods.put("Lentils (1 cup cooked)", new FoodItem("Lentils (1 cup cooked)", 230, 18, 40,20));
        allFoods.put("Peanut Butter (2 tbsp)", new FoodItem("Peanut Butter (2 tbsp)", 188, 8, 7,21));
        allFoods.put("Chia Seeds (1 oz)", new FoodItem("Chia Seeds (1 oz)", 138, 5, 12,22));
        allFoods.put("Turkey Breast (100g)", new FoodItem("Turkey Breast (100g)", 135, 30, 0,23));
        allFoods.put("Cheddar Cheese (1 oz)", new FoodItem("Cheddar Cheese (1 oz)", 113, 7, 1,24));
        allFoods.put("Raspberries (1 cup)", new FoodItem("Raspberries (1 cup)", 64, 1, 15,25));
        allFoods.put("Carrots (1 cup chopped)", new FoodItem("Carrots (1 cup chopped)", 52, 1, 12,26));
        allFoods.put("Hummus (2 tbsp)", new FoodItem("Hummus (2 tbsp)", 60, 2, 4,27));
        allFoods.put("Shrimp (100g)", new FoodItem("Shrimp (100g)", 99, 24, 0,28));
        allFoods.put("Cucumber (1 medium)", new FoodItem("Cucumber (1 medium)", 45, 2, 11,29));
        allFoods.put("Walnuts (1 oz)", new FoodItem("Walnuts (1 oz)", 185, 4, 4,30));
        allFoods.put("Black Beans (1 cup cooked)", new FoodItem("Black Beans (1 cup cooked)", 227, 15, 41,31));
        allFoods.put("Pineapple (1 cup)", new FoodItem("Pineapple (1 cup)", 82, 1, 22,32));
        allFoods.put("Cat(4.5 kg)", new FoodItem("Cat(4.5 kg)", 7425, 1395, 3,33));

        Bundle result = new Bundle();
        result.putSerializable("foodlist", selectedFoods);
        getParentFragmentManager().setFragmentResult("datalist",result);


        return rootView;
    }

    private void setupButton(View rootView, int buttonId, final String mealtime) {
        Button button = rootView.findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFoodSelectionDialog(mealtime);
            }
        });
    }

    private void setupShowSelectedFoodsButton(View rootView,int buttonId) {
        Button button = rootView.findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllSelectedFoods();
            }
        });
    }

    private void showFoodSelectionDialog(final String mealtime) {
        final String[] foodNames = {"Banana","Bread", "Cheese", "Chicken Breast (100g)", "Broccoli (1 cup, chopped)", "Brown Rice (1 cup cooked)", "Salmon (100g)", "Almonds (1 oz)", "Orange", "Sweet Potato (1 medium)", "Greek Yogurt (1 cup)", "Oats (1 cup cooked)", "Egg (1 large)", "Quinoa (1 cup cooked)", "Spinach (1 cup cooked)", "Apple", "Avocado (1 medium)", "Blueberries (1 cup)", "Tofu (100g)", "Lentils (1 cup cooked)", "Peanut Butter (2 tbsp)", "Chia Seeds (1 oz)", "Turkey Breast (100g)", "Cheddar Cheese (1 oz)", "Raspberries (1 cup)", "Carrots (1 cup chopped)", "Hummus (2 tbsp)", "Shrimp (100g)", "Cucumber (1 medium)", "Walnuts (1 oz)", "Black Beans (1 cup cooked)", "Pineapple (1 cup)","Cat(4.5 kg)"};
        final int[] foodCalories = {105, 80, 113, 165, 55, 215, 206, 164, 62, 103, 100, 154, 70, 222, 41, 95, 234, 84, 76, 230, 188, 138, 135, 113, 64, 52, 60, 99, 45, 185, 227, 82, 7425};
        final int[] foodProteins = {1, 3, 7, 31, 4, 5, 22, 6, 1, 2, 10, 6, 6, 8, 5, 0, 3, 1, 8, 18, 8, 5, 30, 7, 1, 1, 2, 24, 2, 4, 15, 1, 1395};
        final int[] foodCarbohydrates = {27, 15, 1, 0, 11, 45, 0, 6, 15, 24, 6, 27, 1, 39, 7, 25, 12, 21, 2, 40, 7, 12, 0, 1, 15, 12, 4, 0, 11, 4, 41, 22, 3};
        final int[] foodFats = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33};


        // Create an array to hold the display strings for each food item
        String[] displayStrings = new String[foodNames.length];
        for (int i = 0; i < foodNames.length; i++) {
            displayStrings[i] = foodNames[i] + " - " + foodCalories[i] + " cal, " +
                    foodProteins[i] + "g proteins, " + foodCarbohydrates[i] + "g carbohydrates, " + foodFats[i] + "g fats";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select Food for " + mealtime);
        builder.setItems(displayStrings, (dialog, which) -> {
            String selectedFoodName = foodNames[which];
            int selectedFoodCalories = foodCalories[which];
            int selectedFoodProteins = foodProteins[which];
            int selectedFoodCarbohydrates = foodCarbohydrates[which];
            int selectedFoodFats = foodFats[which];
            if (totalCalories + selectedFoodCalories > calorieGoal) {
                Toast.makeText(getContext(), "Calorie goal exceeded!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), calorieGoal, Toast.LENGTH_SHORT).show();
                return;
            }
            FoodItem foodItem = new FoodItem(selectedFoodName, selectedFoodCalories, selectedFoodProteins, selectedFoodCarbohydrates, selectedFoodFats);
            selectedFoods.get(mealtime).add(foodItem);
            totalCalories += selectedFoodCalories;
            tvCalories.setText(totalCalories + "/" + calorieGoal);

            // Optionally, you can show the selected foods in the UI
            showSelectedFoods(mealtime);
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void showSelectedFoods(String mealtime) {
        List<FoodItem> foods = selectedFoods.get(mealtime);
        StringBuilder sb = new StringBuilder();
        sb.append(mealtime).append(":\n");
        for (FoodItem food : foods) {
            sb.append(food.getName()).append(" - ").append(food.getCalories()).append(" cal, ")
                    .append(food.getProteins()).append("g proteins, ").append(food.getCarbohydrates())
                    .append("g carbohydrates, ").append(food.getFats()).append("g fats\n");
        }
        Toast.makeText(getContext(), sb.toString(), Toast.LENGTH_LONG).show();
    }

    private void showAllSelectedFoods() {
        StringBuilder sb = new StringBuilder();
        for (String mealtime : selectedFoods.keySet()) {
            List<FoodItem> foods = selectedFoods.get(mealtime);
            sb.append(mealtime).append(":\n");
            for (FoodItem food : foods) {
                sb.append(food.getName()).append(" - ").append(food.getCalories()).append(" cal, ")
                        .append(food.getProteins()).append("g proteins, ").append(food.getCarbohydrates())
                        .append("g carbohydrates, ").append(food.getFats()).append("g fats\n");
            }
            sb.append("\n");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("All Selected Foods");
        builder.setMessage(sb.toString());
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }


}