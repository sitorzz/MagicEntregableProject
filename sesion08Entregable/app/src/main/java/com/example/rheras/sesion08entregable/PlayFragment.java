package com.example.rheras.sesion08entregable;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {

    public final static String EXTRA_MESSAGE = "modoparam1";
    public final static String EXTRA_MESSAGE2 = "modoparam2";
    public String player1name;
    public String player2name;
    PlayerSQLiteHelper playerSQLiteHelper;
    SQLiteDatabase db;
    ArrayList<PlayerModel> jugadores2 = new ArrayList<>();
    ArrayList<PlayerModel> jugadores = new ArrayList<>();
    String nombrejugador;
    ArrayList<String>jugadoresNames = new ArrayList<>();

    ListView listView;


    public PlayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play, container, false);

        playerSQLiteHelper =
                new PlayerSQLiteHelper(this, "players", null, 1);
        db = playerSQLiteHelper.getWritableDatabase();


        //Obtenemos los jugadores de la base de datos
        Cursor c = db.rawQuery(" SELECT name FROM players ", null);

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros

            for (int i = 0; c.moveToNext(); i++) {
                nombrejugador = c.getString(0);
                PlayerModel player = new PlayerModel(nombrejugador, "", 0);

                jugadores.add(player);
            }
        }

        //Creamos la lista
        listView = (ListView) this.findViewById(R.id.listajugadores);

        //Creamos un ArrayAdapter usando nuestro array de jugadores

        for (int i=0;i<jugadores.size();i++){jugadoresNames.add(jugadores.get(i).getName().toString());}

    //    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, jugadoresNames);
        //Aplicamos el ArrayAdapter a la listView
     //   listView.setAdapter(adapter);


        listView.setOnItemClickListener(this);
    }


}
