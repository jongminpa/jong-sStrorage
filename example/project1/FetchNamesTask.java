package com.example.project1;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FetchNamesTask extends AsyncTask<Void, Void, ArrayList<String>> {
    private Context context;
    private ArrayAdapter<String> adapter;

    public FetchNamesTask(Context context, ArrayAdapter<String> adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        ArrayList<String> names = new ArrayList<>();

        try {
            URL url = new URL("http://192.168.0.43/get_names.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                JSONArray jsonArray = new JSONArray(str);
                for (int i = 0; i < jsonArray.length(); i++) {
                    names.add(jsonArray.getString(i));
                }
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return names;
    }

    @Override
    protected void onPostExecute(ArrayList<String> names) {
        super.onPostExecute(names);

        adapter.clear();
        adapter.addAll(names);
        adapter.notifyDataSetChanged();
    }
}

