package org.yamendez.cliapp.github_activity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpService {

    // Optenemos el response de la api de github
    public void getGithubUserActivity() {
        try {
            URL url = new URL("https://api.github.com/users/kamranahmedse/events");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                System.out.println("Error: " + responseCode);
            } else {
                StringBuilder response = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                //System.out.println(response);
                parseJson(response);
                conn.disconnect();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void parseJson(StringBuilder response) {
        // quita los corchetes del inicio y el final
        String json = String.valueOf(response).replaceFirst("^\\[", "")
                .replaceFirst("]$","");

        JSONObject dataObject = new JSONObject(json);
        JSONArray jsonArray = new JSONArray(String.valueOf(response));
        System.out.println(dataObject.names());
        System.out.println(dataObject.getString("type"));

        // arregos: actor, payload, repo
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = (JSONObject) jsonArray.get(i);
            //JSONObject actor = (JSONObject) item.get("actor");
            System.out.println(item.getString("type"));
        }
    }
}
