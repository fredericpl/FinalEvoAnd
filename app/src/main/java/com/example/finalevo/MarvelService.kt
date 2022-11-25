package com.example.finalevo

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MarvelService {
    @Headers("Content-type: application/json")
    @GET("https://api.themoviedb.org/3/search/movie?api_key=1289bdc96c0e3bf709e4789f7a01faf9&language=en-US&page=1&include_adult=false&")

    suspend fun assets(@Query("query") query: String): Response<MovieModel>

}