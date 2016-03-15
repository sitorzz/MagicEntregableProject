package com.example.rheras.sesion08entregable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DataSavesActivity extends AppCompatActivity {

    private TextView lastmatches;

    private MatchModel matchModel;
    private int auxint;
    private ArrayList<Integer> lifes;
    private PlayerModel playerModel;
    private ArrayList<PlayerModel> jugadores;
    private ArrayList<MatchModel> matchModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_saves);



        lastmatches = (TextView) findViewById(R.id.datasaves);

        String matches="";

        loadMatches();

        for(int i=0;i<matchModels.size();i++){

            matches=matches+"\r\n"+"\r\n"+"Partida "+i+" "+matchModels.get(i).toString();
        }

        lastmatches.setText(matches);


    }

    public void loadMatches(){

        matchModels = new ArrayList<>();
        matchModel = new MatchModel();


        auxint=0;

        try {

            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput("registronormal.txt")));

            String aux = "";

            String str = fin.readLine();


            while (str != null) {

                aux = fin.readLine();
                jugadores = new ArrayList<>();

                if (auxint % 3 == 0) {



                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    matchModel.setMyDate(dateFormat.parse(aux));

                } else if (auxint % 3 == 1) {

                    matchModel.setModo(aux);

                } else if (auxint % 3 == 2) {

                    jugadores = new ArrayList<>();
                    lifes = new ArrayList<>();
                    String[] separated = aux.split(";");

                    for (int i = 0; i < separated.length; i++) {

                        String jugador1 = separated[i];

                        String[] param = jugador1.split(",");

                        //rellena 1 jugador

                        playerModel = new PlayerModel();


                        for (int j = 0; j < param.length; j++) {

                            if (j == 0) {
                                playerModel.setName(param[j]);
                            } else if (j == 1) {
                                playerModel.setAvatar(param[j]);
                            } else if (j == 2) {
                                playerModel.setLife(Integer.parseInt(param[j]));
                                lifes.add(Integer.parseInt(param[j]));
                            }
                        }
                        
                        jugadores.add(playerModel);


                    }
                    matchModel.setResultados(lifes);
                    matchModel.setPlayers(jugadores);

                    matchModels.add(matchModel);
                }
                auxint++;
            }





            fin.close();



        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }


    }
}
