package com.example.finalevo.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalevo.Movie
import com.example.finalevo.databinding.SimilarMoviesCellBinding
import com.squareup.picasso.Picasso

class SimilarMoviesAdapter(private val movies: List<Movie>, val listener: ClickerListener): RecyclerView.Adapter<SimilarMoviesAdapter.MoviesRowHolder>() {
    interface ClickerListener{
        fun clickListener(movie: Movie)
    }
    private lateinit var binding: SimilarMoviesCellBinding

    class MoviesRowHolder(var binding: SimilarMoviesCellBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, listener: ClickerListener){
            binding.movieTitle.text = movie.title

            if (!movie.posterPath.isNullOrEmpty()) {
                val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
                Picasso.get()
                    .load(imageUrl)
                    .into(binding.movieImg)
            }

            itemView.setOnClickListener {
                listener.clickListener(movie)
            }



          //  TODO("add movie img")
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesRowHolder {
        binding = SimilarMoviesCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesRowHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesRowHolder, position: Int) {
        holder.bind(movies[position], listener)
    }

    override fun getItemCount(): Int {
        return movies.count()
    }
}