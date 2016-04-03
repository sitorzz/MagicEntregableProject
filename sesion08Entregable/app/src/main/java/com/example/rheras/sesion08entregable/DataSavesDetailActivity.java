package com.example.rheras.sesion08entregable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class DataSavesDetailActivity extends AppCompatActivity {
    private MatchModel matchModel;
    private TextView namesView;
    private TextView result1View;
    private TextView result2View;
    private TextView matchof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_saves_detail);

        Intent intent = getIntent();

        matchModel = (MatchModel) intent.getExtras().getSerializable(DataSavesActivity.EXTRA_MESSAGE_DETAIL);

        namesView = (TextView) findViewById(R.id.names);
        result1View = (TextView) findViewById(R.id.result1);
        result2View = (TextView) findViewById(R.id.result2);
        matchof = (TextView) findViewById(R.id.matchof);

        showResults();


    }

    public void showResults (){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        matchof.setText( "Partida realizada el "+dateFormat.format(matchModel.getMyDate()));
        namesView.setText(matchModel.getPlayers().get(0).getName()+" VS "+matchModel.getPlayers().get(1).getName());

        result1View.setText(matchModel.getResultados().get(0).toString());
        result2View.setText(matchModel.getResultados().get(1).toString());


    }
}
