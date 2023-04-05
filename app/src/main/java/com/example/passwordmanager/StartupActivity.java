package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;

public class StartupActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.starting_layout);

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();

        getWindow().setEnterTransition(fade);

        getWindow().setExitTransition(fade);

        final ImageView imageView = findViewById(R.id.logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartupActivity.this, MainActivity.class);
                // below method is used to make scene transition
                // and adding fade animation in it.
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        StartupActivity.this, imageView, ViewCompat.getTransitionName(imageView));
                // starting our activity with below method.
                startActivity(intent);
            }

        }, SPLASH_DISPLAY_LENGTH);
    }
}
