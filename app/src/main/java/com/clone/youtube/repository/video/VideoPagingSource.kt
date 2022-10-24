package com.clone.youtube.repository.video

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.clone.youtube.model.VideoListItem

class VideoPagingSource(private val service: VideoApiService, private val query: String) :
    PagingSource<Int, VideoListItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoListItem> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response =
                service.getVideoList() //[fix] service.getVideoList(query, nextPageNumber)
            LoadResult.Page(
                data = response.body()!!, // [fix] response.videos
                prevKey = null,
                nextKey = nextPageNumber + params.loadSize // [fix] response.nextPageNumber
            )
        } catch (e: Exception) {
            // network failure
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, VideoListItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
    }

}