package com.example.tmcfc.thanhxuanfc.model;

public class InfoCauThu {
    private int Id;
    private String Tencauthu;
    private String Hinhanhcauthu;
    private String Namsinh;
    private String Chieucao;
    private String Cannang;
    private String Vitri;
    private String Soao;
    private String Diachi;
    private String Idmenu;
    private String Teamid;

    public InfoCauThu(int id, String tencauthu, String hinhanhcauthu, String namsinh, String chieucao, String cannang, String vitri, String soao, String diachi, String idmenu, String teamid) {
        Id = id;
        Tencauthu = tencauthu;
        Hinhanhcauthu = hinhanhcauthu;
        Namsinh = namsinh;
        Chieucao = chieucao;
        Cannang = cannang;
        Vitri = vitri;
        Soao = soao;
        Diachi = diachi;
        Idmenu = idmenu;
        Teamid = teamid;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTencauthu() {
        return Tencauthu;
    }

    public void setTencauthu(String tencauthu) {
        Tencauthu = tencauthu;
    }

    public String getHinhanhcauthu() {
        return Hinhanhcauthu;
    }

    public void setHinhanhcauthu(String hinhanhcauthu) {
        Hinhanhcauthu = hinhanhcauthu;
    }

    public String getNamsinh() {
        return Namsinh;
    }

    public void setNamsinh(String namsinh) {
        Namsinh = namsinh;
    }

    public String getChieucao() {
        return Chieucao;
    }

    public void setChieucao(String chieucao) {
        Chieucao = chieucao;
    }

    public String getCannang() {
        return Cannang;
    }

    public void setCannang(String cannang) {
        Cannang = cannang;
    }

    public String getVitri() {
        return Vitri;
    }

    public void setVitri(String vitri) {
        Vitri = vitri;
    }

    public String getSoao() {
        return Soao;
    }

    public void setSoao(String soao) {
        Soao = soao;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public String getIdmenu() {
        return Idmenu;
    }

    public void setIdmenu(String idmenu) {
        Idmenu = idmenu;
    }

    public String getTeamid() {
        return Teamid;
    }

    public void setTeamid(String teamid) {
        Teamid = teamid;
    }
}
