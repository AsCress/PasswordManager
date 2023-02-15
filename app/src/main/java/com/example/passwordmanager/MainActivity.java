package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editEmail = findViewById(R.id.editEmail);
        EditText editPass = findViewById(R.id.editPass);

        Button loginButton = findViewById(R.id.loginButton);

        TextView errorText = findViewById(R.id.errorText);
        // here we are initializing
        // fade animation.
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();

        // below methods are used for adding
        // enter and exit transition.
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editEmail.getText().toString();
                pass = editPass.getText().toString();

                Log.d("APP", email);
                Log.d("APP", pass);

                if (Objects.equals(email, "test@admin.com") && Objects.equals(pass, "12345678"))
                {
                    Intent intent = new Intent(getApplicationContext(), PasswordsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast toast = Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    errorText.setText("Incorrect ID/Password");
                    errorText.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}