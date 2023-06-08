package com.example.project1;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendDataTask extends AsyncTask<String, Void, Void> {
    private Context context;
    private Runnable callback;

    public SendDataTask(Context context, Runnable callback) {
        this.context = context;
        this.callback = callback;
    }


    @Override
    protected Void doInBackground(String... params) {
        if (params.length < 5) {
            // You can log an error message here or throw a custom exception
            return null;
        }

        String name = params[0];
        String height = params[1];
        String weight = params[2];
        String bmi = params[3];
        String date = params[4];

        try {
            URL url = new URL("http://192.168.0.43/post_data2.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject postData = new JSONObject();
            postData.put("name", name);
            postData.put("height", height);
            postData.put("weight", weight);
            postData.put("bmi", bmi);
            postData.put("date", date);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            out.write(postData.toString());

            out.close();

            // Get the server response code
            int responseCode = conn.getResponseCode();
            if(responseCode != HttpURLConnection.HTTP_OK) {
                // You can log an error message here
                Log.e("SendDataTask", "HTTP error code: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(callback != null) {
            callback.run();
        }
    }
}
