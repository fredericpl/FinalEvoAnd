package com.example.finalevo.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalevo.API_KEY
import com.example.finalevo.Movie
import com.example.finalevo.Services.MarvelServiceImpl
import com.example.finalevo.Adapters.SimilarMoviesAdapter
import com.example.finalevo.databinding.FragmentMovieDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieDetailFragment : Fragment(), SimilarMoviesAdapter.ClickerListener {


    private var binding: FragmentMovieDetailBinding? = null
    private val marvelService by lazy { MarvelServiceImpl() }
    private val args: MovieDetailFragmentArgs by navArgs()
    private val apiKey = "1289bdc96c0e3bf709e4789f7a01faf9"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        similarMovies(args.movieId)
        movie(args.movieId)
        binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.backButton?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun movie(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = marvelService.movie(id)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        binding?.movieTitle?.text = it.title
                        binding?.movieRating?.text = it.voteAverage.toString().subSequence(0..2)
                        binding?.movieSynopsis?.text = it.overview

                        if (!it.posterPath.isNullOrEmpty()) {
                            val imageUrl = "https://image.tmdb.org/t/p/w500${it.posterPath}"
                            Picasso.get()
                                .load(imageUrl)
                                .into(binding?.movieImg)
                        }

                        if (!it.backdropPath.isNullOrEmpty()) {
                            val imageUrl = "https://image.tmdb.org/t/p/w500${it.backdropPath}"
                            Picasso.get()
                                .load(imageUrl)
                                .into(binding?.movieBackImg)
                        }
                    }
                }
            }
        }
    }

    fun similarMovies(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = marvelService.similarMovies(id)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.results?.let {
                        SetupRecyclerView(it)
                    }
                }
            }
        }
    }

    private fun SetupRecyclerView(data: List<Movie>) {
        val recyclerView = binding?.similarMovieRecyclerVue
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView?.adapter = SimilarMoviesAdapter(data, this)

    }

    override fun clickListener(movie: Movie) {
        movie(movie.id)
        similarMovies(movie.id)
    }


}