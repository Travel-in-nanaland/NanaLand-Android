package com.jeju.nanaland.util.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jeju.nanaland.domain.response.ResponsePagingWrapper
import com.jeju.nanaland.domain.response.ResponseWrapper
import retrofit2.HttpException
import retrofit2.Response
import java.text.SimpleDateFormat

interface NetworkResultHandler {
    suspend fun <T : Any?> handleResult(
        responseFunction: suspend () -> Response<ResponseWrapper<T>>
    ): NetworkResult<T> {
        return try {
            val response = responseFunction()
            if (response.isSuccessful) {
                if (response.body() == null) {
                    NetworkResult.Success(response.code(), response.body()?.message, null)
                } else {
                    NetworkResult.Success(response.code(), response.body()!!.message, response.body()!!.data)
                }
            } else {
                val message = ""
                NetworkResult.Error(response.code(), message)
            }
        } catch (e: HttpException) {
            val currentTime = System.currentTimeMillis()
            val format = SimpleDateFormat("yyyy-MM-dd-hh-mm-ss")
            NetworkResult.Error(e.code(), "")
        } catch (e: Throwable) {
            NetworkResult.Exception(e)
        }
    }

    fun <T : Any> handleResultPaging(
        responseFunction: suspend (page: Int, size: Int) -> Response<ResponseWrapper<ResponsePagingWrapper<T>>>
    ): PagingSource<Int, T> {
        return object: PagingSource<Int, T>() {
            override fun getRefreshKey(state: PagingState<Int, T>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }?.takeIf { it > 0 } ?: 0
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
                return try {
                    val currentKey = params.key?.takeIf { it > 0 } ?: 0
                    val call = handleResult {
                        responseFunction(currentKey, params.loadSize)
                    }.onError { code, _ ->
                        throw Exception(code.toString())
                    }.onException {
                        throw it
                    }
                    val data = (call as NetworkResult.Success).data!!.data
                    val prevKey = if(currentKey <= 0) null else currentKey - params.loadSize
                    val nextKey = if(data.isEmpty()) null else currentKey + params.loadSize

                    LoadResult.Page(
                        data = data,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )

                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }
        }

    }
}
