package com.example.moviesapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.moviesapp.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView titleTextView, yearTextView, studioTextView, ratingTextView, plotTextView;
    private ImageView posterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        titleTextView = findViewById(R.id.titleTextView);
        yearTextView = findViewById(R.id.yearTextView);
        studioTextView = findViewById(R.id.studioTextView);
        ratingTextView = findViewById(R.id.ratingTextView);
        plotTextView = findViewById(R.id.plotTextView);
        posterImageView = findViewById(R.id.posterImageView);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        if (movie != null) {
            titleTextView.setText(movie.getTitle());
            yearTextView.setText("Year: " + movie.getYear());
            studioTextView.setText("Studio: " + movie.getStudio());
            ratingTextView.setText("IMDB Rating: " + movie.getRating());
            plotTextView.setText(movie.getPlot());

            Picasso.get().load(movie.getPosterUrl()).into(posterImageView);
        }
    }
}
