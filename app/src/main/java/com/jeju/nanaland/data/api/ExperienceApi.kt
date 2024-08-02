package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.experience.ExperienceContent
import com.jeju.nanaland.domain.entity.experience.ExperienceThumbnailListData
import com.jeju.nanaland.domain.response.ResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExperienceApi {

    // 이색체험 상세 정보 조회
    @GET("experience/{id}")
    suspend fun getExperienceContent(
        @Path("id") id: Int,
        @Query("isSearch") isSearch: Boolean
    ): Response<ResponseWrapper<ExperienceContent>>

    // 이색체험 리스트 조회
    @GET("experience/list")
    suspend fun getExperienceList(
        @Query("experienceType") experienceType: String,
        @Query("keywordFilterList") keywordFilterList: List<String>,
        @Query("addressFilterList") addressFilterList: List<String>,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<ResponseWrapper<ExperienceThumbnailListData>>
}