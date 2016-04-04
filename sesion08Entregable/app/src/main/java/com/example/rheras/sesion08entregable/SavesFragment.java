package com.example.rheras.sesion08entregable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavesFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView lastmatches;
    private MatchModel matchModel;
    private int auxint;
    private ArrayList<Integer> lifes;
    private PlayerModel playerModel;
    private ArrayList<PlayerModel> jugadores;
    private ArrayList<MatchModel> matchModels;
    private ArrayList<Map<String, String>> data;
    public final static String EXTRA_MESSAGE_DETAIL = "detaildata";
   public static SimpleAdapter adapter;


    public SavesFragment() {
        // Required empty public constructor
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        lastmatches = (ListView) getActivity().findViewById(R.id.datasaves);
        DataSavesActivity.loadMatches();
        createDataModel();



        adapter = new SimpleAdapter(getActivity(), data, android.R.layout.simple_list_item_2,new String[]{"title","subtitle"},new int[]{android.R.id.text1,android.R.id.text2});

        lastmatches.setAdapter(adapter);
        lastmatches.setOnItemClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saves, container, false);







    }

    public void loadMatches() {

        matchModels = new ArrayList<>();


        auxint = 0;

        try {

            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    getActivity().openFileInput("registronormal.txt")));

            String aux = "";

            String str = fin.readLine();


            while (str != null) {

                aux = fin.readLine();
                jugadores = new ArrayList<>();

                if (auxint % 3 == 0) {

                    matchModel = new MatchModel();

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

    private void createDataModel() {

        data = new ArrayList<Map<String, String>>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < matchModels.size(); i++) {
            Map<String,String> datum = new HashMap<String,String>(2);

            datum.put("title",matchModels.get(i).getPlayers().get(0).getName()+" VS "+matchModels.get(i).getPlayers().get(1).getName());

            datum.put("subtitle",dateFormat.format(matchModels.get(i).getMyDate()));

            data.add(datum);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intenttodetail = new Intent(getActivity(), DataSavesDetailActivity.class);

        intenttodetail.putExtra(EXTRA_MESSAGE_DETAIL,matchModels.get(position));

        startActivity(intenttodetail);
    }
}
