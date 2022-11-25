package com.example.finalevo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalevo.databinding.FragmentMovieSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieSearchFragment : Fragment(), MovieSearchRowAdapter.ClickerListener {

    private var binding: FragmentMovieSearchBinding? = null
    private val MarvelService by lazy { MarvelServiceImpl()}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        assets()
        binding = FragmentMovieSearchBinding.inflate(layoutInflater, container, false)
        return binding?.root



    }


    fun assets() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = MarvelService.assets()
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
        val recyclerView = binding?.SearchMovieRecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView?.adapter = MovieSearchRowAdapter(data, this)

    }

    override fun clickListener(movie: Movie) {
        findNavController().navigate(MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieDetailFragment())
    }


}