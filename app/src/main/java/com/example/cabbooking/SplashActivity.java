package com.example.cabbooking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Loading screen activity
 */
public class SplashActivity extends AppCompatActivity {

    Handler handler;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        
        // Initialize progress bar
        progressBar = findViewById(R.id.progressBar);
        
        // Start progress bar animation
        startProgressAnimation();
        
        // Start the 3-second timer for login check
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUserLoginStatus();
            }
        }, 3000);
    }

    /**
     * Animate progress bar from 0 to 100% over 3 seconds
     */
    private void startProgressAnimation() {
        Thread progressThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    
                    // Update progress bar on UI thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    
                    try {
                        // Sleep for 30ms to complete in 3 seconds (100 * 30ms = 3000ms)
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        progressThread.start();
    }

    /**
     * Check if user is already logged in and redirect accordingly
     */
    private void checkUserLoginStatus() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        
        if (currentUser != null) {
            // User is already logged in, go directly to MainActivity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            // User is not logged in, go to StartActivity
            Intent intent = new Intent(SplashActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        }
    }
}