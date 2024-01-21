package com.example.urbanwheel;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

public class splace_logo extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splace_logo);






//        Intent iNext = new Intent(splace_logo.this, MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                boolean isLoggedIn = checkUserLoggedIn();
                Intent intent;
                if (isLoggedIn) {
                    intent = new Intent(splace_logo.this, MainActivity.class);

                } else {
                    intent = new Intent(splace_logo.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
//                startActivity(iNext);
//                finish();
            }
        }, 3000);



    }
    private boolean checkUserLoggedIn() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        return preferences.getBoolean("isLoggedIn", false);
    }

}