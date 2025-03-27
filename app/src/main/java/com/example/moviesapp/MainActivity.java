package com.example.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.adapter.MovieAdapter;
import com.example.moviesapp.model.Movie;
import com.example.moviesapp.model.MovieResponse;
import com.example.moviesapp.network.OMDBApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchButton;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    private OMDBApiService apiService;
    private TextView noMoviesTextView;  // TextView to display when no movies are found

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Apply system bar insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.moviesRecyclerView);
        noMoviesTextView = findViewById(R.id.noMoviesTextView);  // Initialize "No movies found" TextView

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, movieList, movie -> {
            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
            intent.putExtra("movie", movie);
            startActivity(intent);
        });

        recyclerView.setAdapter(movieAdapter);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(OMDBApiService.class);

        // Set up search button listener
        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString();
            if (!query.isEmpty()) {
                fetchMovies(query);
            } else {
                Toast.makeText(MainActivity.this, "Please enter a movie title", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Fetch movies from the OMDB API
    private void fetchMovies(String query) {
        String apiKey = "883f98ed";
        Call<MovieResponse> call = apiService.searchMovies(query, apiKey);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();

                    if (movieResponse != null) {
                        System.out.println("API Response: " + new Gson().toJson(movieResponse)); // Debugging output

                        if (movieResponse.getSearch() != null) {
                            movieList.clear();
                            movieList.addAll(movieResponse.getSearch());
                            movieAdapter.notifyDataSetChanged();

                            noMoviesTextView.setVisibility(movieList.isEmpty() ? View.VISIBLE : View.GONE);
                        } else {
                            Toast.makeText(MainActivity.this, "No movies found", Toast.LENGTH_SHORT).show();
                            noMoviesTextView.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Handle failure to connect or other errors
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
