package com.example.rheras.sesion08entregable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.rheras.playerlifecounter.PlayerLifeCounter;

import java.util.ArrayList;

public class CommanderActivity extends AppCompatActivity implements View.OnClickListener {

    public static ArrayList<String> jugadoresNames;
    public static ArrayList <PlayerLifeCounter> playerLifeCounters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commander);

        Bundle aux = getIntent().getExtras();

        playerLifeCounters = new ArrayList<>();

        PlayerLifeCounter playerLifeCounter;

        jugadoresNames = aux.getStringArrayList(PlayActivity.EXTRA_MESSAGE);


        LinearLayout ll = (LinearLayout) findViewById(R.id.myLinearLayout);





        for(int i=0; i<jugadoresNames.size();i++)
        {
            playerLifeCounter = new PlayerLifeCounter(this);

            playerLifeCounter.setPlayerName(jugadoresNames.get(i));
            playerLifeCounter.setTotalLifes("40");
            playerLifeCounters.add(playerLifeCounter);

            assert ll != null;
            ll.addView(playerLifeCounter);
            playerLifeCounters.get(i).setOnClickListener(this);


        }






    }

    @Override
    public void onClick(View v) {



    }
}
