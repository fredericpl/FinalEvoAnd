package com.example.finalevo.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalevo.Movie
import com.example.finalevo.databinding.SearchMovieCellBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class MovieSearchRowAdapter(private val movies: List<Movie>, private val listener: ClickerListener) : RecyclerView.Adapter<MovieSearchRowAdapter.MoviesRowHolder>() {
    interface ClickerListener{
        fun clickListener(movie: Movie)
    }


    private lateinit var binding: SearchMovieCellBinding

    class MoviesRowHolder(var binding: SearchMovieCellBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, listener: ClickerListener) {
            binding.movieTitle.text = movie.title


            fun dateToString(networkDate: String): String {

                if (!networkDate.isNullOrEmpty()) {
                    val dateFormat = SimpleDateFormat("yyyy-mm-dd", Locale.US)
                    val date = dateFormat.parse(networkDate)
                    return SimpleDateFormat("dd/mm/yyyy", Locale.US).format(date)

                }
                    return "you aren't old enough bitch"




            }

            var date = ""
            movie.releaseDate.let {
                if (it != null) {
                    date = it
                }
            }
            binding.movieReleaseDate.text = dateToString(date)
            // TODO add movie img
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