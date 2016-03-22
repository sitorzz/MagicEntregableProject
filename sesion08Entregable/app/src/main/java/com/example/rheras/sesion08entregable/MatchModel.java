package com.example.rheras.sesion08entregable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;



public class MatchModel implements Serializable {

    private Date myDate;
    private String modo;
    private ArrayList<Integer>resultados;
    private ArrayList<PlayerModel> players;

    public MatchModel(Date myDate, String modo, ArrayList<Integer> resultados, ArrayList<PlayerModel> players) {
        this.myDate = myDate;
        this.modo = modo;
        this.resultados = resultados;
        this.players = players;
    }

    public MatchModel() {

    }

    public Date getMyDate() {
        return myDate;
    }

    public void setMyDate(Date myDate) {
        this.myDate = myDate;
    }

    public ArrayList<Integer> getResultados() {

        return resultados;
    }

    public void setResultados(ArrayList<Integer> resultados) {
        this.resultados = resultados;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public ArrayList<PlayerModel> getPlayers() {

        return players;
    }

    public void setPlayers(ArrayList<PlayerModel> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        String result = "";
        String pjs = "";





        for(int i=0;i<getResultados().size();i++){

            result = result+" Vidas jugador "+i+" = "+getResultados().get(i).toString();

        }

        for(int i=0;i<getPlayers().size();i++){

            pjs = pjs+" Jugador "+i+" "+getPlayers().get(i).getName();

        }

        return "MatchModel{" +
                "myDate=" + myDate +
                ", modo='" + modo + '\'' +
                ", resultados=" + result +
                ", players=" + pjs +
                '}';
    }
}
