package com.example.wydarzenia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SignIn extends AsyncTask<String, Integer, String> {
    private TextView statusTV;
    private ProgressBar progressBar;


    //flag 0 means get and 1 means post.(By default it is get.)
    SignIn(TextView statusTV, ProgressBar progressBar) {
        this.statusTV = statusTV;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... arg0) {

        try {
            String username = arg0[0];
            String password = arg0[1];
            String link = arg0[2];

            String data = URLEncoder.encode("username", "UTF-8") + "=" +
                    URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                    URLEncoder.encode(password, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } catch (Exception e) {
            return "Exception: " + e.getMessage();

        }
    }

    @Override
    protected void onPostExecute(String result) {
        progressBar.setVisibility(View.INVISIBLE);
        statusTV.setVisibility(View.VISIBLE);
        statusTV.setText(result);
    }
}

