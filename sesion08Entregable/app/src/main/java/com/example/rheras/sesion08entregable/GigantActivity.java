package com.example.rheras.sesion08entregable;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class GigantActivity extends AppCompatActivity {

    private String pj1;
    private String pj2;
    private TextView pjname1;
    private TextView pjname2;
    private TextView gigantlifes1;
    private TextView gigantlifes2;
    private int lifes1;
    private int lifes2;
    private SimpleDateFormat format;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gigant);

        Bundle aux = getIntent().getExtras();

        pj1 = aux.getString(PlayActivity.EXTRA_MESSAGE);
        pj2 = aux.getString(PlayActivity.EXTRA_MESSAGE2);
        pjname1 = (TextView) findViewById(R.id.gigant1name);
        pjname2 = (TextView) findViewById(R.id.gigant2name);
        gigantlifes1 = (TextView) findViewById(R.id.gigantplayer1lifes);
        gigantlifes2 = (TextView) findViewById(R.id.gigantplayer2lifes);
        pjname1.setText(pj1);
        pjname2.setText(pj2);

        lifes1 = 30;
        lifes2 = 30;

    }

    public void plus (View view){


        switch (view.getId()){

            case R.id.plus1:
                lifes1 ++;
                gigantlifes1.setText(""+lifes1);
                break;

            case R.id.plus2:
                lifes2 ++;
                gigantlifes2.setText(""+lifes2);
                break;

            case R.id.plus15:


                lifes1 = lifes1 + 5;
                gigantlifes1.setText("" + lifes1);

                break;

            case R.id.plus25:

                lifes2 = lifes2 + 5;
                gigantlifes2.setText("" + lifes2);

                break;

        }


    }

    public void minusswitch (View view) throws IOException {


        if((lifes1-1 == 0)  || (lifes2-1) == 0)

        {
            minus(view);
            writeData(view);

        }

        else{

            minus(view);

        }


    }

    public void minus(View view){

        switch (view.getId()){

            case R.id.minus1:
                lifes1 --;

                gigantlifes1.setText(""+lifes1);

                break;

            case R.id.minus2:

                lifes2 --;

                gigantlifes2.setText(""+lifes2);
                break;

            case R.id.minus15:

                if(lifes1>5) {

                    lifes1 = lifes1 - 5;

                    gigantlifes1.setText("" + lifes1);
                }
                break;

            case R.id.minus25:

                if(lifes2>5) {
                    lifes2 = lifes2 - 5;

                    gigantlifes2.setText("" + lifes2);
                }
                break;

        }
    }

    public void writeData(View view){



        pjname1 = (TextView) findViewById(R.id.gigant1name);
        pjname2 = (TextView) findViewById(R.id.gigant2name);


        File file = new File(getFilesDir()+"/registronormal.txt");

        GregorianCalendar calendar = new GregorianCalendar();
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        format.setCalendar(calendar);

        String now = format.format(calendar.getTime());

        if(file.exists()){
            try
            {
                OutputStreamWriter append =
                        new OutputStreamWriter(
                                openFileOutput("registronormal.txt", Context.MODE_APPEND));



                append.write("\r\n"+now+"\r\n"+"gigante de 2 cabezas"+"\r\n"+pjname1.getText().toString()+","+"default"+","+lifes1+";"+pjname2.getText().toString()+","+"default"+","+lifes2);
                append.close();

            }

            catch (Exception ex)
            {

                Log.e("Ficheros", "Error al escribir fichero en la memoria interna");

            }
        }
        else{


            try
            {
                OutputStreamWriter fout =
                        new OutputStreamWriter(
                                openFileOutput("registronormal.txt", Context.MODE_PRIVATE));

                fout.write("comodin"+"\r\n"+now+"\r\n"+"gigante de 2 cabezas"+"\r\n"+pjname1.getText().toString()+","+"default"+","+lifes1+";"+pjname2.getText().toString()+","+"default"+","+lifes2);

                fout.close();

            }

            catch (Exception ex)

            {
                Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
            }


        }

        finish();

    }


}
