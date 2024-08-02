package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.ExperienceApi
import com.jeju.nanaland.domain.entity.experience.ExperienceContent
import com.jeju.nanaland.domain.entity.experience.ExperienceThumbnailListData
import com.jeju.nanaland.domain.repository.ExperienceRepository
import com.jeju.nanaland.domain.request.experience.GetExperienceContentRequest
import com.jeju.nanaland.domain.request.experience.GetExperienceListRequest
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler

class ExperienceRepositoryImpl(
    private val experienceApi: ExperienceApi
) : ExperienceRepository, NetworkResultHandler {

    // 이색체험 상세 정보 조회
    override suspend fun getExperienceContent(
        data: GetExperienceContentRequest
    ): NetworkResult<ExperienceContent> {
        return handleResult {
            experienceApi.getExperienceContent(
                id = data.id,
                isSearch = data.isSearch
            )
        }
    }

    // 이색체험 리스트 조회
    override suspend fun getExperienceList(
        data: GetExperienceListRequest
    ): NetworkResult<ExperienceThumbnailListData> {
        return handleResult {
            experienceApi.getExperienceList(
                experienceType = data.experienceType,
                keywordFilterList = data.keywordFilterList,
                addressFilterList = data.addressFilterList,
                page = data.page,
                size = data.size
            )
        }
    }
}