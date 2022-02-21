package com.daclink.drew.sp22.cst438_project01_starter;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daclink.drew.sp22.cst438_project01_starter.data.UserDAO;
import com.daclink.drew.sp22.cst438_project01_starter.data.UserDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.model.User;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login, register;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();

                if(usernameText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter username or password!", Toast.LENGTH_SHORT).show();
                } else {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDAO userDAO = userDatabase.userDAO();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User user = userDAO.login(usernameText, passwordText);

                            if(user == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid username or password!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        }
                    }).start();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}