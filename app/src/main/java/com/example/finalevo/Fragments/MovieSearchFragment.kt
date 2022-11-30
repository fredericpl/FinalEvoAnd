package com.example.finalevo.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalevo.Movie
import com.example.finalevo.Adapters.MovieSearchRowAdapter
import com.example.finalevo.Services.MarvelServiceImpl
import com.example.finalevo.databinding.FragmentMovieSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieSearchFragment : Fragment(), MovieSearchRowAdapter.ClickerListener {

    private var binding: FragmentMovieSearchBinding? = null
    private val marvelService by lazy { MarvelServiceImpl() }
    private var title: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieSearchBinding.inflate(layoutInflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.searchTextField?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                title = binding?.searchTextField?.query.toString()
                assets(title)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                    title = binding?.searchTextField?.query.toString()
                    assets(title)
                return true
            }
        })
    }

    fun assets(query: String?) {

        if(query.isNullOrEmpty()) {
            setupRecyclerView(listOf())
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                val response = marvelService.assets(query)
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        response.body()?.results?.let {
                            setupRecyclerView(it)
                        }
                    }
                }
            }
        }

    }

    private fun setupRecyclerView(data: List<Movie>) {
        val recyclerView = binding?.SearchMovieRecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView?.adapter = MovieSearchRowAdapter(data, this)

    }

    override fun clickListener(movie: Movie) {
        findNavController().navigate(MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieDetailFragment(movie.id))
    }


}