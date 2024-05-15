package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.request.festival.GetEndedFestivalListRequest
import com.jeju.nanaland.domain.request.festival.GetFestivalContentRequest
import com.jeju.nanaland.domain.request.festival.GetMonthlyFestivalListRequest
import com.jeju.nanaland.domain.request.festival.GetSeasonalFestivalListRequest
import com.jeju.nanaland.domain.response.festival.GetEndedFestivalListResponse
import com.jeju.nanaland.domain.response.festival.GetFestivalContentResponse
import com.jeju.nanaland.domain.response.festival.GetMonthlyFestivalListResponse
import com.jeju.nanaland.domain.response.festival.GetSeasonalFestivalListResponse
import com.jeju.nanaland.util.network.NetworkResult

interface FestivalRepository {

    // 축제 상세 정보 조회
    suspend fun getFestivalContent(
        data: GetFestivalContentRequest
    ): NetworkResult<GetFestivalContentResponse>

    // 월별 축제 리스트 조회
    suspend fun getMonthlyFestivalList(
        data: GetMonthlyFestivalListRequest
    ): NetworkResult<GetMonthlyFestivalListResponse>

    // 계절별 축제 리스트 조회
    suspend fun getSeasonalFestivalList(
        data: GetSeasonalFestivalListRequest
    ): NetworkResult<GetSeasonalFestivalListResponse>

    // 종료된 축제 리스트 조회
    suspend fun getEndedFestivalList(
        data: GetEndedFestivalListRequest
    ): NetworkResult<GetEndedFestivalListResponse>
}