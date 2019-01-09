package com.example.jj.androidappforavtomat.gitHubAuth;

import java.util.ArrayList;

interface ISecondStepAuthActivity extends IAuth{

    public String getLogin();

    public String getPassword();

    public String get2Auth();

    void setLogin(String name);

    void setRepo(ArrayList<String> repoList);

    void activateNextActivity(String login, String password, Boolean auth2);

}
