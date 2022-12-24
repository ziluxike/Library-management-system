package com.domain;

public class Reader {
    private Integer id;
    private String idReader;
    private String nameReader;
    private String kind;
    private String sex;
    private String password;

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", idReader='" + idReader + '\'' +
                ", nameReader='" + nameReader + '\'' +
                ", kind='" + kind + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdReader() {
        return idReader;
    }

    public void setIdReader(String idReader) {
        this.idReader = idReader;
    }

    public String getNameReader() {
        return nameReader;
    }

    public void setNameReader(String nameReader) {
        this.nameReader = nameReader;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
