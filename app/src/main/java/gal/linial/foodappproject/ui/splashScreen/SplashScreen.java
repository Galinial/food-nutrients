package gal.linial.foodappproject.ui.splashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import gal.linial.foodappproject.R;
import gal.linial.foodappproject.ui.Login.LoginActivity;

public class SplashScreen extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;



    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        lottieAnimationView = findViewById(R.id.food_logo);
        lottieAnimationView.setProgress(0);
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this , LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3250);
    }
}
