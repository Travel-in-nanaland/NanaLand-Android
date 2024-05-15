package com.jeju.nanaland.util.network

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