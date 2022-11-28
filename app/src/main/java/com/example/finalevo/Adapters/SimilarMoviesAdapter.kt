package com.example.finalevo.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalevo.Movie
import com.example.finalevo.databinding.SimilarMoviesCellBinding

class SimilarMoviesAdapter(private val movies: List<Movie>): RecyclerView.Adapter<SimilarMoviesAdapter.MoviesRowHolder>() {

    private lateinit var binding: SimilarMoviesCellBinding

    class MoviesRowHolder(var binding: SimilarMoviesCellBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie){
            binding.movieTitle.text = movie.title
          //  TODO("add movie img")
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesRowHolder {
        binding = SimilarMoviesCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesRowHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesRowHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.count()
    }
}