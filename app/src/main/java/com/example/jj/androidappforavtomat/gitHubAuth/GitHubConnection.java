package com.example.jj.androidappforavtomat.gitHubAuth;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import static android.support.constraint.Constraints.TAG;

class GitHubConnection {
    private static IAuth iAuth;
    private static boolean auth2Factor = false;
    GitHubConnection(IAuth iAuth) {
        GitHubConnection.iAuth = iAuth;
    }
   void connectToGithub () {
        String encoding =  Base64.encodeToString(
                (iAuth.getLogin() + ":" + iAuth.getPassword()).getBytes(),
                Base64.DEFAULT);
        AuthAsyncTask authAsyncTask = new AuthAsyncTask();
        authAsyncTask.execute(encoding, iAuth.get2Auth());
    }

    static class AuthAsyncTask extends AsyncTask<String, Void, Void> {
        private ArrayList<String> repoList = new ArrayList<>();
        private boolean hasErrors = false;

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!hasErrors || auth2Factor) {
                String login = iAuth.getLogin();
                String password = iAuth.getPassword();
                iAuth.setRepo(repoList);
                iAuth.setLogin(login);
                iAuth.activateNextActivity(login, password, hasErrors);

            }
        }

        @Override
        protected Void doInBackground(String... strings) {

            SSLContext sslContext = null;
            try {
                sslContext = SSLContext.getInstance("TLSv1.2");
                sslContext.init(null, null, null);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            SSLSocketFactory NoSSLv3Factory = new NoSSLv3SocketFactory(sslContext.getSocketFactory());

            HttpsURLConnection.setDefaultSSLSocketFactory(NoSSLv3Factory);
            HttpURLConnection connection = null;
            BufferedReader bufferedReader = null;
            hasErrors = false;
            try {
                URL location = new URL("https://api.github.com/user/repos");
                connection = (HttpURLConnection) location.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(false);
                connection.setRequestProperty("Authorization", "Basic " + strings[0].trim()
                        .replaceAll("\\n", ""));
                connection.setRequestProperty("X-GitHub-OTP", strings[1]);
                InputStream content = null;
                try {
                    content = connection.getInputStream();
                } catch (Exception ex) {
                    content = connection.getErrorStream();
                    Log.d(TAG,ex.toString());
                }
                bufferedReader = new BufferedReader(
                        new InputStreamReader(content));
                String result = bufferedReader.readLine();

                if (result.toLowerCase().contains("two-factor authentication") && auth2Factor) {
                    hasErrors = true;
                } else if (result.toLowerCase().contains("two-factor authentication")) {
                    auth2Factor = true;
                } else {
                    JSONArray array = new JSONArray(result);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jObj = array.getJSONObject(i);
                        repoList.add(jObj.getString("name"));
                    }
                }
            } catch (Exception e) {
                hasErrors = true;
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            return null;
        }

    }

    public static boolean isAuth2Factor() {
        return auth2Factor;
    }
}
