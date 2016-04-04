package com.example.rheras.sesion08entregable;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

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


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_play);

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

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, jugadoresNames);
            //Aplicamos el ArrayAdapter a la listView
            listView.setAdapter(adapter);


            listView.setOnItemClickListener(this);
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




            if(!listView.isItemChecked(position)){

                listView.setItemChecked(position,false);
              for(int i=0;i<jugadores2.size();i++){


                  if(jugadores2.get(i).getLife()==position){

                      jugadores2.remove(i);

                  }
              }

            }else {

                listView.setItemChecked(position, true);

                PlayerModel player = new PlayerModel();
                player.setName(listView.getItemAtPosition(position).toString());
                player.setLife(position);

               jugadores2.add(player);


            }
        }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_create_new:
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentRegister);
                return true;

            case R.id.action_menu:
                return true;

        } return true;
    }


        public void normal (View view){


            if(jugadores2.size()>2){

                Toast.makeText(this, "Máximo 2 jugadores!!", Toast.LENGTH_SHORT).show();

                }

            else if(jugadores2.size()<2){

                Toast.makeText(this, "Mínimo 2 jugadores!!", Toast.LENGTH_SHORT).show();
                }


            else{ Intent intent = new Intent(this,NormalActivity.class);


                Bundle normalBundle = new Bundle();
                player1name = jugadores2.get(0).getName();
                player2name = jugadores2.get(1).getName();
                normalBundle.putString(EXTRA_MESSAGE,player1name);
                normalBundle.putString(EXTRA_MESSAGE2,player2name);

                 intent.putExtras(normalBundle); // put extras en plurar al pasarle un bundle

                startActivity(intent);}


        }

        public void gigant (View view){


            if(jugadores2.size()>2){

                Toast.makeText(this, "Máximo 2 jugadores!!", Toast.LENGTH_SHORT).show();

            }

            else if(jugadores2.size()<2){

                Toast.makeText(this, "Mínimo 2 jugadores!!", Toast.LENGTH_SHORT).show();
            }


            else{
                player1name = jugadores2.get(0).getName();
            player2name = jugadores2.get(1).getName();

            Intent intent = new Intent(this,GigantActivity.class);


            Bundle gigantBundle = new Bundle();

            gigantBundle.putString(EXTRA_MESSAGE,player1name);
            gigantBundle.putString(EXTRA_MESSAGE2,player2name);

            intent.putExtras(gigantBundle); // put extras en plurar al pasarle un bundle

            startActivity(intent);



        }
        }

        public void dataSaves (View view){

            Intent intent = new Intent(this, DataSavesActivity.class);

            startActivity(intent);

        }
}
