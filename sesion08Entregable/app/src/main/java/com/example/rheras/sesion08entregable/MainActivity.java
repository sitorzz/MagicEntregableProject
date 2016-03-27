package com.example.rheras.sesion08entregable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void play(View view){

        Intent intent = new Intent(this, PlayActivity.class);

        startActivity(intent);

    }

    public void signup(View view){
    Intent intent  = new Intent(this,RegisterActivity.class);
        startActivity(intent);

    }


}
