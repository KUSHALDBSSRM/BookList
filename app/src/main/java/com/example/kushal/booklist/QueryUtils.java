package com.example.kushal.booklist;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class QueryUtils {

   static  String Json_url = "https://www.googleapis.com/books/v1/volumes?q=";
    public QueryUtils()
    {

    }
    public static ArrayList<Books> extractFeatures(String Query){
        HttpURLConnection urlConnection;
        InputStream inputStream = null;
        String json_response = "";
        ArrayList<Books> books = new ArrayList<>();
        try{
        //Json_url+=Query;
        URL url = new URL(Query);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.connect();
        inputStream = urlConnection.getInputStream();
        json_response = readFromStream(inputStream);
        JSONObject json_response_object = new JSONObject(json_response);
        JSONArray arr = json_response_object.getJSONArray("items");
       for(int i=0;i< arr.length();i++)
        {
            JSONObject prop = arr.getJSONObject(i);
            JSONObject prop1 = prop.getJSONObject("volumeInfo");
            String title = prop1.getString("title");
            JSONArray arr1 = prop1.getJSONArray("authors");
            String author = arr1.getString(0);
            books.add(new Books(title,author));
        }

    } catch (Exception e){
        e.printStackTrace();
    }
           return books;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
