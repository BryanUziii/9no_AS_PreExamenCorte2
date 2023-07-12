package com.example.a9no_as_preexamencorte2;

public class Usuario {
    int id;
    String userName;
    String correo;
    String password;

    public Usuario() {
    }

    public Usuario(int id, String userName, String correo, String password){
        this.id = id;
        this.userName = userName;
        this.correo = correo;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
