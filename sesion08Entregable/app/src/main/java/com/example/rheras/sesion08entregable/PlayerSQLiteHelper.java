package com.example.rheras.sesion08entregable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AlbertMartinez on 22/3/16.
 */
public class PlayerSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate =  "CREATE TABLE players (name TEXT, avatar TEXT, life INT)";

    public PlayerSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS players");
        db.execSQL(sqlCreate);

    }
}
