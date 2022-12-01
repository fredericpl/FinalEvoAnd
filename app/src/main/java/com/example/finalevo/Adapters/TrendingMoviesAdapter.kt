package com.example.finalevo.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalevo.Movie
import com.example.finalevo.databinding.TrendingCellBinding
import com.squareup.picasso.Picasso

class TrendingMoviesAdapter(private val movies: List<Movie>, private val listener: ClickerListener): RecyclerView.Adapter<TrendingMoviesAdapter.MoviesRowHolder>() {
    interface ClickerListener{
        fun clickListener(movie: Movie)
    }
    private lateinit var binding: TrendingCellBinding

    class MoviesRowHolder(var binding: TrendingCellBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, listener: ClickerListener){

            binding.movieRating.text = movie.voteAverage.toString().subSequence(0..2)

            if (!movie.posterPath.isNullOrEmpty()) {
                val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
                Picasso.get()
                    .load(imageUrl)
                    .into(binding.movieImg)
            }

            itemView.setOnClickListener {
                listener.clickListener(movie)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesRowHolder {
        binding = TrendingCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesRowHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesRowHolder, position: Int) {
        holder.bind(movies[position], listener)
    }

    override fun getItemCount(): Int {
        return movies.count()
    }
}