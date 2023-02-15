package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PasswordsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwords);

        recyclerView = (RecyclerView) findViewById(R.id.password_list);
        FAB = (FloatingActionButton) findViewById(R.id.buttonAdd);

        getSupportActionBar().setTitle("Saved Passwords");

        DBHelper helper = new DBHelper(this);

        ArrayList<PasswordModel> passwordModelArrayList = new ArrayList<>();
        passwordModelArrayList.clear();

        passwordModelArrayList = helper.readPasswords();

        PasswordAdapter passwordAdapter = new PasswordAdapter(this, passwordModelArrayList, new PasswordAdapter.ItemClickCallBack() {
            @Override
            public void onItemClickCallBack(PasswordModel item) {

            }

            @Override
            public void onMenuClickCallBack(int position, int id) {
                switch (id)
                {
                    case R.id.update:
                    {

                    }
                    case R.id.delete:
                    {
                        // helper.deleteWorkout(id);
                    }
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(passwordAdapter);



        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PasswordsActivity.this, AddPasswords.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper helper = new DBHelper(this);

        ArrayList<PasswordModel> passwordModelArrayList = new ArrayList<>();
        passwordModelArrayList.clear();

        passwordModelArrayList = helper.readPasswords();

        PasswordAdapter passwordAdapter = new PasswordAdapter(this, passwordModelArrayList, new PasswordAdapter.ItemClickCallBack() {
            @Override
            public void onItemClickCallBack(PasswordModel item) {

            }

            @Override
            public void onMenuClickCallBack(int position, int id) {
                switch (position)
                {
                    case R.id.update:
                    {
                        helper.deleteWorkout(id+1);
                        Intent intent = new Intent(PasswordsActivity.this, UpdateActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.delete:
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Deleted successfully", Toast.LENGTH_LONG);
                        toast.show();
                        helper.deleteWorkout(id+1);
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        break;
                    }
                }
            }
        });


        recyclerView.setAdapter(passwordAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(passwordAdapter);



    }
}