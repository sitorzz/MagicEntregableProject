package com.example.rheras.sesion08entregable;


import java.io.Serializable;

public class PlayerModel implements Serializable {

    private String name;
    private String avatar;
    private int life;

    public PlayerModel(String name, String avatar,int life) {
        this.name = name;
        this.avatar = avatar;
    }

    public PlayerModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
