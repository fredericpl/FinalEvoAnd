package com.example.finalevo.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalevo.Movie
import com.example.finalevo.databinding.SearchMovieCellBinding

class MovieSearchRowAdapter(private val movies: List<Movie>, val listener: ClickerListener) : RecyclerView.Adapter<MovieSearchRowAdapter.MoviesRowHolder>() {
    interface ClickerListener{
        fun clickListener(movie: Movie)
    }


    private lateinit var binding: SearchMovieCellBinding

    class MoviesRowHolder(var binding: SearchMovieCellBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, listener: ClickerListener) {
            binding.movieTitle.text = movie.title
            binding.movieReleaseDate.text = movie.releaseDate
            // TODO add movie img


            itemView.setOnClickListener {
                listener.clickListener(movie)
            }

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesRowHolder {
        binding = SearchMovieCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  MoviesRowHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesRowHolder, position: Int) {
        holder.bind(movies[position], listener)
    }

    override fun getItemCount(): Int {
        return movies.count()
    }
}