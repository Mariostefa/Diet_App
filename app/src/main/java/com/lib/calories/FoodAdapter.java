package com.lib.calories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> foodList;
    private HashMap<String, Integer> foodCalories;
    private LayoutInflater inflater;

    public FoodAdapter(Context context, ArrayList<String> foodList, HashMap<String, Integer> foodCalories) {
        this.context = context;
        this.foodList = foodList;
        this.foodCalories = foodCalories;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.food_item, parent, false);
        }

        ImageView ivFoodIcon = convertView.findViewById(R.id.ivFoodIcon);
        TextView tvFoodName = convertView.findViewById(R.id.tvFoodName);
        TextView tvFoodCalories = convertView.findViewById(R.id.tvFoodCalories);

        String foodName = foodList.get(position);
        int calories = foodCalories.get(foodName);

        // Set food icon - Replace with actual icons for each food item if available
        switch (foodName) {
            case "Apple":
                ivFoodIcon.setImageResource(R.drawable.apple);
                break;
            case "Banana":
                ivFoodIcon.setImageResource(R.drawable.banana);
                break;
            case "Orange":
                ivFoodIcon.setImageResource(R.drawable.orange);
                break;
            case "Bread":
                ivFoodIcon.setImageResource(R.drawable.bread);
                break;
            case "Cheese":
                ivFoodIcon.setImageResource(R.drawable.cheese);
                break;
            default:
                ivFoodIcon.setImageResource(R.drawable.nofood); // Placeholder icon
                break;
        }

        tvFoodName.setText(foodName);
        tvFoodCalories.setText(calories + " cal");

        return convertView;
    }
}