package com.example.ace.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String user_id;
    String email;
    int submit;
    int solved;
    String defunct;
    String ip;
    String datatime;
    int volume;
    int lanuage;
    String password;
    String reg_time;
    String nick;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSubmit() {
        return submit;
    }

    public void setSubmit(int submit) {
        this.submit = submit;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    public String getDefunct() {
        return defunct;
    }

    public void setDefunct(String defunct) {
        this.defunct = defunct;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getLanuage() {
        return lanuage;
    }

    public void setLanuage(int lanuage) {
        this.lanuage = lanuage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", email='" + email + '\'' +
                ", submit=" + submit +
                ", solved=" + solved +
                ", defunct='" + defunct + '\'' +
                ", ip='" + ip + '\'' +
                ", datatime='" + datatime + '\'' +
                ", volume=" + volume +
                ", lanuage=" + lanuage +
                ", password='" + password + '\'' +
                ", reg_time='" + reg_time + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }

}
