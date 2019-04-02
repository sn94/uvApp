package com.example.sonia.uvapp.retrofits;


public class Data {
    public Data(String imagebase64) {//Aqui deberia ir la foto
        this.foto  = imagebase64;
    }

    private String foto;


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}