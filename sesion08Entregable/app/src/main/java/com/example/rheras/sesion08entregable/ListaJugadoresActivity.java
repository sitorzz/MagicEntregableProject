package com.example.rheras.sesion08entregable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListaJugadoresActivity extends AppCompatActivity {

    PlayerSQLiteHelper playerSQLiteHelper;
    SQLiteDatabase db;
    String[] jugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jugadores);
        db = playerSQLiteHelper.getWritableDatabase();


      //Obtenemos los jugadores de la base de datos
      Cursor c = db.rawQuery(" SELECT name FROM players ", null);

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros

            for(int i=0; c.moveToNext(); i++){
                c.getString(i);
            }
        }

        //Creamos la lista
        ListView listView = (ListView) this.findViewById(R.id.listajugadores);
        //Creamos un ArrayAdapter usando nuestro array de jugadores
        ArrayAdapter adapter = new ArrayAdapter (this, android.R.layout.simple_list_item_checked, jugadores);
        //Aplicamos el ArrayAdapter a la listView
        listView.setAdapter(adapter);


    }


}
