package com.example.finalevo.Services

import android.app.DownloadManager.Query
import com.example.finalevo.API_KEY
import com.example.finalevo.BASEURL
import com.example.finalevo.Movie
import com.example.finalevo.MovieModel
import okhttp3.OkHttpClient
import org.intellij.lang.annotations.Language
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MarvelServiceImpl: MarvelService {


    private fun getRetrofit(): Retrofit {

        
        val okBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(120, TimeUnit.SECONDS)
            callTimeout(120, TimeUnit.SECONDS)
            readTimeout (120, TimeUnit.SECONDS)
            writeTimeout(120, TimeUnit.SECONDS)
        }
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okBuilder.build())
            .build()
    }
    override suspend fun assets(query: String, apiKey: String, includeAdult: Boolean ,language: String): Response<MovieModel> {
        return getRetrofit().create(MarvelService::class.java).assets(query)

    }

    override suspend fun movie(id: Int, apiKey: String, language: String): Response<Movie> {
        return getRetrofit().create(MarvelService::class.java).movie(id)
    }

    override suspend fun similarMovies(id: Int, apiKey: String, language: String): Response<MovieModel> {
        return getRetrofit().create(MarvelService::class.java).similarMovies(id)
    }

    override suspend fun trendingMovies(apiKey: String): Response<MovieModel> {
        return getRetrofit().create(MarvelService::class.java).trendingMovies()
    }
}