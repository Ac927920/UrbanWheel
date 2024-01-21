package com.example.urbanwheel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    EditText uname, passwd;
    Button login_sub_btn;
    ImageView login_imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname = findViewById(R.id.uname);
        passwd = findViewById(R.id.passwd);
        login_sub_btn = findViewById(R.id.login_sub_btn);
        login_imageView = findViewById(R.id.login_imageView);

        login_sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password;
                username = String.valueOf(uname.getText()).trim();
                password = String.valueOf(passwd.getText()).trim();

                if (TextUtils.isEmpty(username)){
                    Toast.makeText(LoginActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (username.equals("ad") && password.equals("ad")){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Enter Valid Username and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}