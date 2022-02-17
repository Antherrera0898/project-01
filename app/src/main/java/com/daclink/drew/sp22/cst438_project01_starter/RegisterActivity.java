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

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, name;
    Button register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);

        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();

                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.setName(name.getText().toString());

                if(validate(user)){
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDAO userDAO = userDatabase.userDAO();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDAO.registerUser(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "All signed up!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill in everything!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    Boolean validate(User user){
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getName().isEmpty()){
            return false;
        }

        return true;
    }
}
