package com.example.flixter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String backdropPath;
    String posterPath;
    String title;
    String overview;

    public Movie(JSONObject j) throws JSONException {
        posterPath = j.getString("poster_path");
        backdropPath = j.getString("backdrop_path");
        title = j.getString("title");
        overview = j.getString("overview");
    }

    public static List<Movie> fromJSONArray(JSONArray array) throws JSONException {
        List<Movie> movies = new ArrayList<Movie>();
        for(int i = 0; i < array.length(); i++){
            movies.add(new Movie(array.getJSONObject(i)));
        }
        return movies;

    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s", backdropPath);
    }
}
