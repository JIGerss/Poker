package com.steveny.pojo;

public class User {
    private String name;
    private String psw;
    private Integer uuid;

    public User(String name, String password, Integer uuid) {
        this.name = name;
        this.psw = password;
        this.uuid = uuid;
    }

    public User(String name, String password) {
        this.name = name;
        this.psw = password;
    }

    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + psw + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}
