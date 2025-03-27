package com.example.moviesapp.model;

import java.util.List;

public class MovieResponse {
    private List<Movie> Search;
    private String Response;

    // Getters and Setters
    public List<Movie> getSearch() {
        return Search;
    }

    public void setSearch(List<Movie> search) {
        Search = search;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
