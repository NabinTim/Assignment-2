package com.example.moviesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.databinding.ItemMovieBinding;
import com.example.moviesapp.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final Context context;
    private final List<Movie> movieList;
    private final MovieClickListener movieClickListener;

    public interface MovieClickListener {
        void onMovieClick(Movie movie);
    }

    public MovieAdapter(Context context, List<Movie> movieList, MovieClickListener movieClickListener) {
        this.context = context;
        this.movieList = movieList;
        this.movieClickListener = movieClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.binding.movieTitleTextView.setText(movie.getTitle());
        holder.binding.movieYearTextView.setText("Year: " + (movie.getYear() != null ? movie.getYear() : "N/A"));
        holder.binding.movieStudioTextView.setText("Studio: " + (movie.getStudio() != null ? movie.getStudio() : "Unknown"));
        holder.binding.movieRatingTextView.setText("Rating: " + (movie.getRating() != null ? movie.getRating() : "N/A"));

        holder.binding.getRoot().setOnClickListener(v -> movieClickListener.onMovieClick(movie));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding binding;

        public MovieViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
