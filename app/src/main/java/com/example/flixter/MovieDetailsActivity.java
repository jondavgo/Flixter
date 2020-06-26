package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixter.databinding.ActivityMovieDetailsBinding;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;
    TextView tvTitle;
    TextView tvOverview;
    TextView tvDate;
    RatingBar rbRatings;
    ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        assert movie != null;
        Log.d("MovieDetails", "Showing details for: " + movie.getTitle());
        tvTitle = binding.tvTitle;
        tvOverview = binding.tvOverview;
        rbRatings = binding.rbRating;
        tvDate = binding.tvDate;
        ivPoster = binding.ivPoster;

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        float rating = movie.getVoteAverage().floatValue();
        rbRatings.setRating(rating > 0 ? rating / 2.0f : rating);
        tvDate.setText(String.format("Released: %s", movie.getReleaseDate()));
        String imageURL = movie.getBackdropPath();
        int placeholder = R.drawable.flicks_backdrop_placeholder;
        Glide.with(this)
                .load(imageURL)
                .transform(new RoundedCornersTransformation(30, 10))
                .placeholder(placeholder)
                .into(ivPoster);
    }
}