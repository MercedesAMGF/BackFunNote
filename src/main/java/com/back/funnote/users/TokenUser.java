package com.back.funnote.users;

public class TokenUser {

    private String username;
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenUser(String userName, String token) {
        this.username = userName;
        this.token = token;
    }

    public TokenUser() {
    }
}
