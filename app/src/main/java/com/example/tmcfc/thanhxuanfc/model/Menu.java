package com.example.tmcfc.thanhxuanfc.model;

public class Menu {
    public int Id;
    public String Tenmenu;
    public String Hinhanhmenu;

    public Menu(int id, String tenmenu, String hinhanhmenu) {
        Id = id;
        Tenmenu = tenmenu;
        Hinhanhmenu = hinhanhmenu;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenmenu() {
        return Tenmenu;
    }

    public void setTenmenu(String tenmenu) {
        Tenmenu = tenmenu;
    }

    public String getHinhanhmenu() {
        return Hinhanhmenu;
    }

    public void setHinhanhmenu(String hinhanhmenu) {
        Hinhanhmenu = hinhanhmenu;
    }
}
