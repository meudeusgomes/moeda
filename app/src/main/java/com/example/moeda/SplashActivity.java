package com.example.moeda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashActivity extends Activity {

    // TEMPO DE EXIBIÇÃO DO SPLASH (2 SEGUNDOS)
    private static final int SPLASH_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_splash); // DEFINE O LAYOUT DO SPLASH

        // ATRASO PARA EXIBIR O SPLASH NO TEMPO DETERMIDADO
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent); // INICIA MAINACTIVITY
                finish(); // FECHA O SPLASH
            }
        }, SPLASH_TIMEOUT);
    }
}
