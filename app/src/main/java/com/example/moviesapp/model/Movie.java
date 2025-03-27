package com.example.moviesapp.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("imdbRating")
    private String rating;

    @SerializedName("Production")
    private String studio;

    @SerializedName("Plot")
    private String plot;

    @SerializedName("Poster")
    private String posterUrl;

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating != null ? rating : "N/A";  // Handle null case
    }

    public String getStudio() {
        return studio != null ? studio : "Unknown"; // Handle null case
    }

    public String getPlot() {
        return plot;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
}
