package com.example.rheras.sesion08entregable;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {

   PlayerSQLiteHelper playerSQLiteHelper;
    SQLiteDatabase db;
    private static int RESULT_LOAD_IMG = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    byte[] img;
    String imgDecodableString;
    Bitmap imageBitmap;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         playerSQLiteHelper=
                new PlayerSQLiteHelper(this, "players", null, 1);
        db = playerSQLiteHelper.getWritableDatabase();
    }


    public void registerPlayers(View view) {




      //   img=Utilities.getBytes(BitmapFactory.decodeResource(getResources(), R.drawable.cute));

        ContentValues values = new ContentValues();
        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        EditText editTextLife = (EditText) findViewById(R.id.editTextLife);

        String picturePath = "";


        File internalStorage = playerSQLiteHelper.context.getDir("PlayerPictures", Context.MODE_PRIVATE);
        File playerFilePath = new File(internalStorage, editTextName.getText().toString() + ".png");

        picturePath = playerFilePath.toString();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(playerFilePath);
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100 /*quality*/, fos);
            fos.close();
        }
        catch (Exception ex) {
            Log.i("DATABASE", "Problem updating picture", ex);
            picturePath = "";
        }


        values.put("name", editTextName.getText().toString());

        values.put("life",editTextLife.getText().toString());

        values.put("image",picturePath);


        db.insert("players", null, values);
        db.close();
    }



    public void loadImagefromGallery(View view) {

        // Create intent to Open Image applications like Gallery, Google Photos

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
       // finish();

    }

    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
             imageBitmap = (Bitmap) extras.get("data");
            ImageView imgView = (ImageView) findViewById(R.id.imgView);
            imgView.setImageBitmap(imageBitmap);
            img=Utilities.getBytes(imageBitmap);
        }



    }



}
