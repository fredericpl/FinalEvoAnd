package com.example.finalevo.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalevo.Movie
import com.example.finalevo.databinding.SimilarMoviesCellBinding
import com.example.finalevo.databinding.TrendingCellBinding
import com.squareup.picasso.Picasso

class TrendingMoviesAdapter(private val movies: List<Movie>): RecyclerView.Adapter<TrendingMoviesAdapter.MoviesRowHolder>() {

    private lateinit var binding: TrendingCellBinding

    class MoviesRowHolder(var binding: TrendingCellBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie){

            binding.movieRating.text = movie.voteAverage.toString().subSequence(0..2)
            if (!movie.posterPath.isNullOrEmpty()) {
                val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
                Picasso.get()
                    .load(imageUrl)
                    .into(binding.movieImg)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesRowHolder {
        binding = TrendingCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesRowHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesRowHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.count()
    }
}