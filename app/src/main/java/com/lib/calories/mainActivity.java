package com.lib.calories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class mainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    private SharedPreferences sharedPreferences;

    homeFragment homeFragment = new homeFragment();
    programFragment programFragment = new programFragment();
    profileFragment profileFragment = new profileFragment();

    recipesFragment recipesFragment = new recipesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int newUiOptions =  activity.getWindow().getDecorView().getSystemUiVisibility();
        newUiOptions ^= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        newUiOptions ^= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        activity.getWindow().getDecorView().setSystemUiVisibility(newUiOptions);


        bottomNavigationView =findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.miHome){
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,homeFragment).addToBackStack("home").commit();
                } else if (item.getItemId() == R.id.miProgram) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, programFragment).addToBackStack("program").commit();
                } else if (item.getItemId() == R.id.miSettings) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,profileFragment).addToBackStack("profile").commit();
                }else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,recipesFragment).addToBackStack("recipe").commit();
                }
                return true;
            }
        });


    }


}