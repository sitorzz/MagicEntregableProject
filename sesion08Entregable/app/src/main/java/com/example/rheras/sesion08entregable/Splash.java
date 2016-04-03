package com.example.rheras.sesion08entregable;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Thread myThread = new Thread(){

            @Override
            public void run() {

                try {
                    sleep(2000);

                    Intent startMainScreen = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(startMainScreen);

                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        };
        myThread.start();
    }
}
