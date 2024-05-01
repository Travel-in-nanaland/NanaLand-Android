package com.nanaland.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nanaland.data.api.MemberApi
import com.nanaland.domain.repository.MemberRepository
import com.nanaland.domain.request.member.SignInRequest
import com.nanaland.domain.request.member.UpdateUserTypeRequest
import com.nanaland.domain.response.member.GetRecommendedPostResponse
import com.nanaland.domain.response.member.ReissueAccessTokenResponse
import com.nanaland.domain.response.member.SignInResponse
import com.nanaland.domain.response.member.UpdateUserTypeResponse
import com.nanaland.globalvalue.constant.KEY_ACCESS_TOKEN
import com.nanaland.globalvalue.constant.KEY_REFRESH_TOKEN
import com.nanaland.util.network.NetworkResult
import com.nanaland.util.network.NetworkResultHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MemberRepositoryImpl(
    private val memberApi: MemberApi
) : MemberRepository, NetworkResultHandler {

    // 유저 타입에 따른 추천 게시물 2개 반환
    override suspend fun getRecommendedPost(): NetworkResult<GetRecommendedPostResponse> {
        return handleResult { memberApi.getRecommendedPost() }
    }

    // 테스트 결과에 따른 유저 타입 갱신
    override suspend fun updateUserType(
        data: UpdateUserTypeRequest
    ): NetworkResult<UpdateUserTypeResponse> {
        return handleResult {
            memberApi.updateUserType(
                body = data
            )
        }
    }
}