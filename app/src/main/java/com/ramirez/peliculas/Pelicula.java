package com.ramirez.peliculas;

import java.io.Serializable;

public class Pelicula implements Serializable {

    private String nombre;
    private int img;
    private boolean aBoolean;

    public Pelicula(String nombre, int img, Boolean aBoolean) {
        this.nombre = nombre;
        this.img = img;
        this.aBoolean = aBoolean;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
}
