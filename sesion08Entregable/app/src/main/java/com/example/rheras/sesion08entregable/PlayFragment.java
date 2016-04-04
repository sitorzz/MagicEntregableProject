package com.example.rheras.sesion08entregable;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment implements AdapterView.OnItemClickListener{

    SQLiteDatabase db;
      PlayerSQLiteHelper playerSQLiteHelper;


    public PlayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         playerSQLiteHelper =
                new PlayerSQLiteHelper(getContext(), "players", null, 1);
        db = playerSQLiteHelper.getWritableDatabase();
        PlayActivity.jugadores = new ArrayList<>();
        PlayActivity.jugadores2 = new ArrayList<>();
        PlayActivity.jugadoresNames = new ArrayList<>();

        //Obtenemos los jugadores de la base de datos
        Cursor c = db.rawQuery(" SELECT name FROM players ", null);

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros

            for (int i = 0; c.moveToNext(); i++) {
                PlayActivity.nombrejugador = c.getString(0);
                PlayerModel player = new PlayerModel(PlayActivity.nombrejugador, "", 0);

                PlayActivity.jugadores.add(player);
            }
        }

        //Creamos la lista
        PlayActivity.listView = (ListView) getActivity().findViewById(R.id.listajugadores);

        //Creamos un ArrayAdapter usando nuestro array de jugadores

        for (int i=0;i<PlayActivity.jugadores.size();i++){PlayActivity.jugadoresNames.add(PlayActivity.jugadores.get(i).getName().toString());}

        //    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, jugadoresNames);
        //Aplicamos el ArrayAdapter a la listView
        //   listView.setAdapter(adapter);


        PlayActivity.listView.setOnItemClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play, container, false);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(!PlayActivity.listView.isItemChecked(position)){

            PlayActivity.listView.setItemChecked(position,false);
            for(int i=0;i<PlayActivity.jugadores2.size();i++){


                if(PlayActivity.jugadores2.get(i).getLife()==position){

                    PlayActivity.jugadores2.remove(i);

                }
            }

        }else {

            PlayActivity.listView.setItemChecked(position, true);

            PlayerModel player = new PlayerModel();
            player.setName(PlayActivity.listView.getItemAtPosition(position).toString());
            player.setLife(position);

            PlayActivity.jugadores2.add(player);


        }
    }





}

