package com.example.finalevo.Services

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.finalevo.Movie
import kotlinx.coroutines.flow.Flow


class movieViewModel(repository: MovieRepository): ViewModel() {

    val items : java.util.concurrent.Flow<PagingData<Movie>> : Pager(
    config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
    pagingSourceFactory = { repository }
    )
}