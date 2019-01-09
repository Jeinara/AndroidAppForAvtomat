package com.example.jj.androidappforavtomat.gitHubAuth;

import java.util.ArrayList;

public interface IAuth {
    void setLogin(String name);
    void setRepo(ArrayList<String> repoList);
    public String getLogin();
    public String getPassword();
    public String get2Auth();
    void activateNextActivity(String login, String password, Boolean auth2);
}
