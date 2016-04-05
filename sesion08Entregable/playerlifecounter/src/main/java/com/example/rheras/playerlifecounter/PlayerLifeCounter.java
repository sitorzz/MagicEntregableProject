package com.example.rheras.playerlifecounter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class PlayerLifeCounter extends LinearLayout implements View.OnClickListener {


    private TextView playerName;
    private TextView totalLife;
    private int lifes;
    private Button minus,minusFive,plus,plusFive;
    private onLifeChangedListener mCallback=null;


    public interface onLifeChangedListener{

        public void onLifeChangedExecuted(PlayerLifeCounter source, int totalLife);

    }

    public void setOnLifeChanged(onLifeChangedListener listener) {

        mCallback = listener;

    }

    public PlayerLifeCounter(Context context) {
        super(context);
        init (null,0);
    }


    public PlayerLifeCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init (attrs,0);
    }

    public PlayerLifeCounter(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init (attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.player_life_counter, this);

        playerName = (TextView) findViewById(R.id.playerName);
        totalLife = (TextView) findViewById(R.id.playerTotalLifes);
        minus = (Button) findViewById(R.id.minus1);
        minusFive = (Button) findViewById(R.id.minus15);
        plus = (Button) findViewById(R.id.plus1);
        plusFive = (Button) findViewById(R.id.plus15);
        lifes=40;
        TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.PlayerLifeCounter, defStyle, 0);

        CharSequence s = a.getString(R.styleable.PlayerLifeCounter_playerName);

        if(s!= null){setPlayerName(s.toString());}

        CharSequence s2 = a.getString(R.styleable.PlayerLifeCounter_totalLifes);

        if(s!= null){setTotalLifes(s2.toString());}

        a.recycle();

        minus.setOnClickListener(this);
        minusFive.setOnClickListener(this);
        plus.setOnClickListener(this);
        plusFive.setOnClickListener(this);

    }

    public void setPlayerName(String playerNameText){

        playerName.setText(playerNameText);

        invalidate();
        requestLayout();

    }

    public void setTotalLifes(String totalLifesText){

        totalLife.setText(totalLifesText);
        invalidate();
        requestLayout();

    }

    @Override
    public void onClick(View v) {


        int i = v.getId();


        if (lifes != 0) {

            if (i == R.id.plus1) {


                lifes++;


            } else if (i == R.id.plus15) {


                lifes = lifes + 5;

            } else if (i == R.id.minus1) {

                lifes--;

            } else if (i == R.id.minus15) {

                if (lifes > 5) {

                    lifes = lifes - 5;

                }

            }

            totalLife.setText("" + lifes);
        }

        if (mCallback != null) {
            mCallback.onLifeChangedExecuted(this, lifes);
        }



    }

}