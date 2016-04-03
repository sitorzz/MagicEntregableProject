package com.example.rheras.sesion08entregable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class PlayActivity extends AppCompatActivity {

        public final static String EXTRA_MESSAGE = "modoparam1";
        public final static String EXTRA_MESSAGE2 = "modoparam2";
        public String player1name;
        public String player2name;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_play);
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_create_new:
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.action_menu:

                break;
        }
    }


        public void normal (View view){


            EditText player1 = (EditText) findViewById(R.id.player1);

            player1name = player1.getText().toString();

            EditText player2 = (EditText) findViewById(R.id.player2);

            player2name = player2.getText().toString();

            Intent intent = new Intent(this,NormalActivity.class);


            Bundle normalBundle = new Bundle();

            normalBundle.putString(EXTRA_MESSAGE,player1name);
            normalBundle.putString(EXTRA_MESSAGE2,player2name);

            intent.putExtras(normalBundle); // put extras en plurar al pasarle un bundle

            startActivity(intent);



        }

        public void gigant (View view){

            EditText player1 = (EditText) findViewById(R.id.player1);

            player1name = player1.getText().toString();

            EditText player2 = (EditText) findViewById(R.id.player2);

            player2name = player2.getText().toString();

            Intent intent = new Intent(this,GigantActivity.class);


            Bundle gigantBundle = new Bundle();

            gigantBundle.putString(EXTRA_MESSAGE,player1name);
            gigantBundle.putString(EXTRA_MESSAGE2,player2name);

            intent.putExtras(gigantBundle); // put extras en plurar al pasarle un bundle

            startActivity(intent);



        }

        public void dataSaves (View view){

            Intent intent = new Intent(this, DataSavesActivity.class);

            startActivity(intent);

        }
}
