package com.example.rheras.sesion08entregable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataSavesActivity extends AppCompatActivity{

    private ListView lastmatches;
    private MatchModel matchModel;
    private int auxint;
    private ArrayList<Integer> lifes;
    private PlayerModel playerModel;
    private ArrayList<PlayerModel> jugadores;
    private ArrayList<MatchModel> matchModels;
    private ArrayList<Map<String, String>> data;
    public final static String EXTRA_MESSAGE_DETAIL = "detaildata";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_saves);




    }


}