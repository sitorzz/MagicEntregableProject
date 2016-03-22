package com.example.rheras.sesion08entregable;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    PlayerSQLiteHelper playerSQLiteHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    public void registerPlayers(View view) {
        playerSQLiteHelper =
                new PlayerSQLiteHelper(this, "players", null, 1);
        db = playerSQLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        EditText editTextCodigo = (EditText) findViewById(R.id.editTextCodigo);
        EditText editTextNombre = (EditText) findViewById(R.id.editTextNombre);

        values.put("name", editTextCodigo.getText().toString());
        values.put("image", editTextNombre.getText().toString());
        values.put("life",editTextNombre)


        db.insert("Alumnos", null, values);
        db.close();
    }




}
