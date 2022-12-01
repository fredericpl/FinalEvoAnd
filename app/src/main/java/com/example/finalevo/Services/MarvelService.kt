package com.example.finalevo.Services

import com.example.finalevo.API_KEY
import com.example.finalevo.LANGUAGE
import com.example.finalevo.Movie
import com.example.finalevo.MovieModel
import org.intellij.lang.annotations.Language
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query




interface MarvelService {

    @Headers("Content-type: application/json")
    @GET("search/movie")
    suspend fun assets(@Query("query") query: String,
                       @Query("api_key", encoded = false) apiKey: String = API_KEY,
                       @Query("include_adult", encoded = false) includeAdult: Boolean = false,
                       @Query("language", encoded = false) language: String = LANGUAGE

    ): Response<MovieModel>

    @Headers("Content-type: application/json")
    @GET("movie/{id}")
    suspend fun movie(@Path("id") id: Int,
                      @Query("api_key", encoded = false) apiKey: String = API_KEY,
                      @Query("language", encoded = false) language: String = LANGUAGE
    ): Response<Movie>

    @Headers("Content-type: application/json")
    @GET("movie/{id}/similar")
    suspend fun similarMovies(@Path("id") id: Int,
                              @Query("api_key", encoded = false) apiKey: String = API_KEY,
                              @Query("language", encoded = false) language: String = LANGUAGE
    ): Response<MovieModel>

    @Headers("Content-type: application/json")
    @GET("trending/all/day")
    suspend fun trendingMovies(@Query("api_key", encoded = false) apiKey: String = API_KEY): Response<MovieModel>

}