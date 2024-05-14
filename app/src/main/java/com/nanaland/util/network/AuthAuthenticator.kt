package com.nanaland.util.network

import android.util.Log
import com.nanaland.domain.usecase.authdatastore.GetRefreshTokenUseCase
import com.nanaland.domain.usecase.auth.ReissueAccessTokenUseCase
import com.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

//class AuthAuthenticator @Inject constructor(
//    private val getRefreshTokenUseCase: GetRefreshTokenUseCase,
//    private val reissueAccessTokenUseCase: ReissueAccessTokenUseCase,
//    private val saveAccessTokenUseCase: SaveAccessTokenUseCase
//) : Authenticator {
//    override fun authenticate(route: Route?, response: Response): Request? {
//        val newAccessToken = runBlocking {
//            val refreshToken = getRefreshTokenUseCase().first()
//            if (refreshToken.isNullOrEmpty()) {
//                response.close()
//                return@runBlocking null
//            }
//            var accessToken = ""
//            reissueAccessTokenUseCase(refreshToken).first().onSuccess { code, item ->
//                accessToken = item?.data ?: ""
//                saveAccessTokenUseCase(accessToken)
//
//            }.onError { _, _ ->
//                response.close()
//            }.onException {
//                response.close()
//            }
//            accessToken
//        }
//        if (newAccessToken.isNullOrEmpty()) {
//            response.close()
//            return null
//        }
//        Log.e("AuthAuthenticator", "new AccessToken: ${newAccessToken}")
//
//        return newRequestWithToken(newAccessToken, response.request)
//    }
//
//    private fun newRequestWithToken(token: String, request: Request): Request =
//        request.newBuilder()
//            .header("Authorization", "Bearer $token")
//            .build()
//}