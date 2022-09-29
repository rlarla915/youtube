package com.clone.youtube.model.offline

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.clone.youtube.model.MainVideoListItem

class OfflinePagingSource(private val dao : OfflineVideoDao, val query: String)
    :PagingSource<Int, OfflineVideo>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OfflineVideo> {
        try {
            val nextPageNumber = params.key ?:1
            val response = dao.getAllVideo()
            return LoadResult.Page(
                data = response, // [fix] response.videos
                prevKey = if (nextPageNumber == 1) null else nextPageNumber-1,
                nextKey = nextPageNumber + (params.loadSize/10)
            )
        } catch (e : Exception){
            // network failure
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, OfflineVideo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
    }


}