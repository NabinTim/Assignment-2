package com.example.moviesapp.network;

import com.example.moviesapp.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDBApiService {

    // Base URL: https://www.omdbapi.com/
    @GET("/")
    Call<MovieResponse> searchMovies(
            @Query("s") String query,    // Movie search query
            @Query("apikey") String apiKey
    );
}
