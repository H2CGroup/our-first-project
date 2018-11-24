package com.example.tmcfc.thanhxuanfc.model;

public class UserInformation {
    public String name;
    public String nickname;
    public String imgavatar;

    public UserInformation() {

    }

    public UserInformation(String name, String nickname, String imgavatar) {
        this.name = name;
        this.nickname = nickname;
        this.imgavatar = imgavatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImgavatar() {
        return imgavatar;
    }

    public void setImgavatar(String imgavatar) {
        this.imgavatar = imgavatar;
    }
}
