package com.shurik.pizzaplanet;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.shurik.pizzaplanet.databinding.ActivityIntroductoryBinding;

public class IntroductoryActivity extends AppCompatActivity {

    private ActivityIntroductoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroductoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        animate();
        Thread thread = new Thread(new MyThread());
        thread.start();
    }

    // анимация
    public void animate() {
        binding.img.animate().translationY((-1600))
                .setDuration(600).setStartDelay(2170);

        binding.logo.animate().translationY((1400))
                .setDuration(600).setStartDelay(2170);

        binding.appName.animate().translationY((1400))
                .setDuration(600).setStartDelay(2170);

        binding.lottie.animate().translationY((1500))
                .setDuration(600).setStartDelay(2170);
    }

    private void transition(){
        Intent intent = new Intent(IntroductoryActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    class MyThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2405);
            } catch (InterruptedException e) {}
            transition();
        }
    }
}