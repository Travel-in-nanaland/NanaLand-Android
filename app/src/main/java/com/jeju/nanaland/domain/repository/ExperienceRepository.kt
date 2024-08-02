package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.entity.experience.ExperienceContent
import com.jeju.nanaland.domain.entity.experience.ExperienceThumbnailListData
import com.jeju.nanaland.domain.request.experience.GetExperienceContentRequest
import com.jeju.nanaland.domain.request.experience.GetExperienceListRequest
import com.jeju.nanaland.util.network.NetworkResult

interface ExperienceRepository {

    // 이색체험 상세 정보 조회
    suspend fun getExperienceContent(
        data: GetExperienceContentRequest
    ): NetworkResult<ExperienceContent>

    // 이색체험 리스트 조회
    suspend fun getExperienceList(
        data: GetExperienceListRequest
    ): NetworkResult<ExperienceThumbnailListData>
}