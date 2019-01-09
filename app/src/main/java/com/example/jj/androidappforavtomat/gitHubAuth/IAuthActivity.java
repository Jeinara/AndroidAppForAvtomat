package com.example.jj.androidappforavtomat.gitHubAuth;

import android.view.View;

import java.util.ArrayList;

interface IAuthActivity extends IAuth {
    public void onAuthButtonClick(View view);

    public String getLogin();

    public String getPassword();

    public String get2Auth();

    void setRepo(ArrayList<String> repoList);

    void setLogin(String name);

    void activateNextActivity(String login, String password, Boolean auth2);
}
