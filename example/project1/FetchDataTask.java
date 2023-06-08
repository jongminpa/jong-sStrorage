package com.example.project1;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class FetchDataTask extends AsyncTask<String, Void, List<String>> {
    private Context context;
    private ListView listView;

    public FetchDataTask(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected List<String> doInBackground(String... params) {
        List<String> data = new ArrayList<>();
        // Fetch the data from the server
        try {
            String name = params[0]; // assuming you are sending the 'name' as a parameter to this AsyncTask
            URL url = new URL("http://192.168.0.43/get_data.php?name=" + URLEncoder.encode(name, "UTF-8"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            reader.close();

            // Now parse the JSON
            JSONArray jsonArray = new JSONArray(sb.toString()); // assuming the server returns a JSON array
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String userData = "id: "+ jsonObject.getString( "user_id") + ", Name: " + jsonObject.getString("name") + ", Height: " + jsonObject.getString("height")
                        + ", Weight: " + jsonObject.getString("weight") + ", BMI: " + jsonObject.getString("bmi")
                        + ", Date: " + jsonObject.getString("date");
                data.add(userData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(List<String> result) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                result);
        listView.setAdapter(adapter);
    }
}

