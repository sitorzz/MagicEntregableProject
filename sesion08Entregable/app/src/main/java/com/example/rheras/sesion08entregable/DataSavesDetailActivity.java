package com.example.rheras.sesion08entregable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DataSavesDetailActivity extends AppCompatActivity {
    private MatchModel matchModel;
    private String prueba;
    private TextView namesView;
    TextView resultsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_saves_detail);

        Intent intent = getIntent();

        matchModel = (MatchModel) intent.getExtras().getSerializable(DataSavesActivity.EXTRA_MESSAGE_DETAIL);

        namesView = (TextView) findViewById(R.id.names);
        resultsView = (TextView) findViewById(R.id.results);

        showResults();


    }

    public void showResults (){


        namesView.setText("Partida de "+matchModel.getPlayers().get(0).getName()+" VS "+matchModel.getPlayers().get(1).getName());
        resultsView.setText("Resultados "+matchModel.getResultados().get(0).toString()+" / "+matchModel.getPlayers().get(1).toString());



    }
}
