package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.FestivalApi
import com.jeju.nanaland.domain.entity.festival.FestivalContentData
import com.jeju.nanaland.domain.repository.FestivalRepository
import com.jeju.nanaland.domain.request.festival.GetEndedFestivalListRequest
import com.jeju.nanaland.domain.request.festival.GetFestivalContentRequest
import com.jeju.nanaland.domain.request.festival.GetMonthlyFestivalListRequest
import com.jeju.nanaland.domain.request.festival.GetSeasonalFestivalListRequest
import com.jeju.nanaland.domain.entity.festival.EndedFestivalListData
import com.jeju.nanaland.domain.entity.festival.MonthlyFestivalListData
import com.jeju.nanaland.domain.entity.festival.SeasonalFestivalListData
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler

class FestivalRepositoryImpl(
    private val festivalApi: FestivalApi
) : FestivalRepository, NetworkResultHandler {

    // 축제 상세 정보 조회
    override suspend fun getFestivalContent(
        data: GetFestivalContentRequest
    ): NetworkResult<FestivalContentData> {
        return handleResult {
            festivalApi.getFestivalContent(
                id = data.id,
                isSearch = data.isSearch
            )
        }
    }

    // 월별 축제 리스트 조회
    override suspend fun getMonthlyFestivalList(
        data: GetMonthlyFestivalListRequest
    ): NetworkResult<MonthlyFestivalListData> {
        return handleResult {
            festivalApi.getMonthlyFestivalList(
                page = data.page,
                size = data.size,
                addressFilterList = data.addressFilterList,
                startDate = data.startDate,
                endDate = data.endDate
            )
        }
    }

    // 계절별 축제 리스트 조회
    override suspend fun getSeasonalFestivalList(
        data: GetSeasonalFestivalListRequest
    ): NetworkResult<SeasonalFestivalListData> {
        return handleResult {
            festivalApi.getSeasonalFestivalList(
                page = data.page,
                size = data.size,
                season = data.season
            )
        }
    }

    // 종료된 축제 리스트 조회
    override suspend fun getEndedFestivalList(
        data: GetEndedFestivalListRequest
    ): NetworkResult<EndedFestivalListData> {
        return handleResult {
            festivalApi.getEndedFestivalList(
                page = data.page,
                size = data.size,
                addressFilterList = data.addressFilterList,
            )
        }
    }
}