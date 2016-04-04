package com.example.rheras.sesion08entregable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaJugadoresActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    PlayerSQLiteHelper playerSQLiteHelper;
    SQLiteDatabase db;
    ArrayList<String> jugadores = new ArrayList<String>();
    String nombrejugador;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jugadores);
        playerSQLiteHelper =
                new PlayerSQLiteHelper(this, "players", null, 1);
        db = playerSQLiteHelper.getWritableDatabase();


        //Obtenemos los jugadores de la base de datos
        Cursor c = db.rawQuery(" SELECT name FROM players ", null);

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros

            for (int i = 0; c.moveToNext(); i++) {
                nombrejugador = c.getString(0);
                jugadores.add(nombrejugador);
            }
        }

        //Creamos la lista
         listView = (ListView) this.findViewById(R.id.listajugadores);
        //Creamos un ArrayAdapter usando nuestro array de jugadores
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, jugadores);
        //Aplicamos el ArrayAdapter a la listView
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(listView.getCheckedItemCount()<3){
        if(!listView.isItemChecked(position)){
            listView.setItemChecked(position,false);
        }else {
            listView.setItemChecked(position, true);
        }}else{
            Toast.makeText(this, "Máximo 2 jugadores!!", Toast.LENGTH_SHORT).show();
            listView.setItemChecked(position,false);
        }

    }
}
