package com.example.finalevo

import com.google.gson.annotations.SerializedName


data class MovieModel(
    val results: List<Movie>,
    val page: Int,
    @SerializedName("totalPages")
    val total_pages: Int ,
    @SerializedName("totalResults")
    val total_results: Int

)


data class Movie (
    @SerializedName("voteAverage")
    val vote_average: Float,
    val id : Int,
    @SerializedName("posterPath")
    val poster_path: String?,
    @SerializedName("genreIDS")
    val genre_ids: List<Int>?,
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val overview: String,
    @SerializedName("backdropPath")
    val backdrop_path: String?
)