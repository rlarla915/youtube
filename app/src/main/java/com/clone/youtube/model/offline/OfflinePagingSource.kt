package com.clone.youtube.model.offline

import androidx.paging.PagingSource
import androidx.paging.PagingState

class OfflinePagingSource(private val dao: OfflineVideoDao, private val query: String) :
    PagingSource<Int, OfflineVideo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OfflineVideo> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = dao.getAllVideo()
            LoadResult.Page(
                data = response, // [fix] response.videos
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = nextPageNumber + (params.loadSize / 10)
            )
        } catch (e: Exception) {
            // network failure
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, OfflineVideo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
    }

}