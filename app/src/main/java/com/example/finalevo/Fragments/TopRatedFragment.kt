package com.example.finalevo.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalevo.Adapters.MovieSearchRowAdapter
import com.example.finalevo.Adapters.TrendingMoviesAdapter
import com.example.finalevo.Movie
import com.example.finalevo.Services.MarvelService
import com.example.finalevo.Services.MarvelServiceImpl
import com.example.finalevo.databinding.FragmentTopRatedBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TopRatedFragment : Fragment() {

    private var binding: FragmentTopRatedBinding? = null
    private val marvelService by lazy { MarvelServiceImpl() }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopRatedBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trending()
    }

    fun trending() {

            CoroutineScope(Dispatchers.IO).launch {
                val response = marvelService.trendingMovies()
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        response.body()?.results?.let {
                            setupRecyclerView(it)
                        }
                    }
                }

        }

    }

    private fun setupRecyclerView(data: List<Movie>) {
        val recyclerView = binding?.trendingRecyclerView
        recyclerView?.layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
        recyclerView?.adapter = TrendingMoviesAdapter(data)
    }

}