package com.domain;

public class Admin {
    private Integer id;
    private String username;
    private String password;

    public Admin() {
    }

    public Admin(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "librarian{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername(String username) {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(String password) {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
