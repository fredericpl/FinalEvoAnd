package com.example.finalevo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalevo.databinding.FragmentMovieDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieDetailFragment : Fragment() {


    private var binding: FragmentMovieDetailBinding? = null
    private val marvelService by lazy { MarvelServiceImpl() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        assets()
        binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    fun assets() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = marvelService.assets()
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
        recyclerView?.adapter = SimilarMoviesAdapter(data)

    }


}