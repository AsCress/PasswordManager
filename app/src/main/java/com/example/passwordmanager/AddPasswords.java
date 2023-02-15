package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPasswords extends AppCompatActivity {
    private String web, user, pass;
    EditText w,u,p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_passwords);

        getSupportActionBar().setTitle("Add Password");

        w = (EditText)findViewById(R.id.website);
        u = (EditText)findViewById(R.id.userName);
        p = (EditText)findViewById(R.id.password);

        Button b1 = (Button)findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertToDB();
            }
        });
    }

    private void insertToDB() {
        web = w.getText().toString();
        user = u.getText().toString();
        pass = p.getText().toString();

        DBHelper helper = new DBHelper(this);

        helper.insertValues(web, user, pass);

        this.finish();
    }
}
