package com.jeju.nanaland.domain.navigation

import com.jeju.nanaland.globalvalue.constant.TravelType
import kotlinx.serialization.Serializable


sealed class ROUTE {
    @Serializable
    /** 스플래시 화면 **/
    data object Splash: ROUTE() {
        @Serializable
        /** 언어 선택 화면 **/
        data object LanguageSetting: ROUTE() // ROUTE_LANGUAGE_INITIALIZATION
        @Serializable
        /** 로그인 화면 **/
        data object SignIn: ROUTE() { // ROUTE_SIGN_IN
            @Serializable
            /** 약관 동의 화면 **/
            data class PolicyAgree(val provider: String, val email: String, val providerId: String): ROUTE() { // ROUTE_POLICY_AGREE
                @Serializable
                /** 이용약관 동의 및 개인정보 처리방침 화면 **/
                data object Detail: ROUTE() // ROUTE_PRIVACY_POLICY_DETAILS
                @Serializable
                /** 마케팅 활용 동의 화면 **/
                data object Marketing: ROUTE() // ROUTE_MARKETING_POLICY_DETAILS
                @Serializable
                /** 위치기반 서비스 약관 동의 화면 **/
                data object Location: ROUTE() // ROUTE_LOCATION_POLICY_DETAILS
                @Serializable
                /** 회원가입 프로필 설정 화면 **/
                data class SignUp( // ROUTE_SIGN_UP
                    val provider: String, val email: String, val providerId: String,
                    val isPrivacyPolicyAgreed: Boolean,
                    val isMarketingPolicyAgreed: Boolean,
                    val isLocationPolicyAgreed: Boolean
                ): ROUTE()
            }
        }
    }


    @Serializable
    data class Report(val reportId: Int, val isReview: Boolean): ROUTE()

    @Serializable
    /** 유형 테스트별 추천 여행지 화면 **/
    data object RecommendedSpot: ROUTE()

    @Serializable
    /** 시작 메인 화면 **/
    data object Main: ROUTE() {

        @Serializable
        /** 메인 홈 화면 **/
        data object Home: ROUTE() {
            @Serializable
            data object Notification: ROUTE()
            @Serializable
            data object Search: ROUTE()
        }
        @Serializable
        /** 찜 화면 **/
        data object Favorite: ROUTE()

        @Serializable
        /** 나나픽 화면 **/
        data object NanaPick: ROUTE() { // ROUTE_RESTAURANT_LIST
            @Serializable
            /** 나나픽 전체보기 화면 **/
            data object AllList: ROUTE() // ROUTE_RESTAURANT_CONTENT
            @Serializable
            /** 나나픽 상세 화면 **/
            data class Detail(val contentId: Int): ROUTE() // ROUTE_RESTAURANT_CONTENT
        }
        @Serializable
        /** 프로필 화면 **/
        data class Profile(
            val userId: Int? = null // null: 마이 페이지, not null: 타인 페이지
        ): ROUTE() {
            @Serializable
            data class StartDest(val userId: Int? = null): ROUTE()

            @Serializable
            /** 프로필 수정 화면 **/
            data class Update(
                val profileImageUri: String,
                val nickname: String,
                val introduction: String
            ): ROUTE()

            @Serializable
            /** 프로필 공지사항 리스트 화면 **/
            data object NoticeList: ROUTE() {
                @Serializable
                data class NoticeDetail(val noticeId: Int): ROUTE()
            }

            @Serializable
            /** 프로필 리뷰 리스트 화면 **/
            data class ReviewList(val initialScrollToItemId: Int?): ROUTE()

            @Serializable
            data object Setting: ROUTE() {
                @Serializable
                data object Policy: ROUTE()
                @Serializable
                data object PermissionChecking: ROUTE()
                @Serializable
                data object LanguageChange: ROUTE()
                @Serializable
                data object Withdraw: ROUTE()
            }
        }


    }

    data object Content { // 컨텐츠 들
        @Serializable
        /** 자연 리스트 화면 **/
        data class Nature(val filter: String? = null): ROUTE() { // ROUTE_NATURE_LIST
            @Serializable
            /** 자연 상세 화면 **/
            data class Detail(val contentId: Int, val isSearch: Boolean=false): ROUTE() // ROUTE_NATURE_CONTENT
        }
        @Serializable
        /** 축제 리스트 화면 **/
        data class Festival(val filter: String? = null): ROUTE() { // ROUTE_FESTIVAL_LIST
            @Serializable
            /** 축제 상세 화면 **/
            data class Detail(val contentId: Int, val isSearch: Boolean=false): ROUTE() // ROUTE_FESTIVAL_CONTENT
        }
        @Serializable
        /** 전통시장 리스트 화면 **/
        data object Market: ROUTE() { // ROUTE_MARKET_LIST
            @Serializable
            /** 전통시장 상세 화면 **/
            data class Detail(val contentId: Int, val isSearch: Boolean=false): ROUTE() // ROUTE_MARKET_CONTENT
        }

        @Serializable
        /** 액티비티 or 이색체험 리스트 화면 **/
        data class Experience(val isActivity: Boolean): ROUTE() {
            @Serializable
            /** 액티비티 or 이색체험 상세 화면 **/
            data class Detail(val isActivity: Boolean, val contentId: Int, val isSearch: Boolean=false): ROUTE()
        }
        @Serializable
        /** 제주 맛집 리스트 화면 **/
        data object Restaurant: ROUTE() { // ROUTE_RESTAURANT_LIST
            @Serializable
            /** 제주 맛집 상세 화면 **/
            data class Detail(val contentId: Int, val isSearch: Boolean=false): ROUTE() // ROUTE_RESTAURANT_CONTENT
        }
        @Serializable
        /** 후기 작성 화면 **/
        data class ReviewWrite(
            val id: Int? = null,
            val category: String? = null,
            val isEdit: Boolean = false
        ): ROUTE() {
            @Serializable
            data object StartViewForRouting: ROUTE()
            @Serializable
            data object Search: ROUTE()
            @Serializable
            data class Write(
                val id: Int,
                val category: String
            ): ROUTE()
            @Serializable
            data object Keyword: ROUTE()
            @Serializable
            data class Complete(val category: String): ROUTE()
        }
        @Serializable
        data class ReviewList(
            val isFavorite: Boolean?,
            val contentId: Int?,
            val category: String?,
            val thumbnailUrl: String?,
            val contentTitle: String?,
            val contentAddress: String?,
        ): ROUTE()
    }

    @Serializable
    /** 정보 수정 제안 카테고리 선택 화면 **/
    data class InformationModification(
        val postId: Int,
        val category: String,
    ): ROUTE() {
        @Serializable
        /** 정보 수정 제안 작성 화면 **/
        data class Write(
            val postId: Int,
            val category: String,
            val fixType: String
        ): ROUTE()
        @Serializable
        /** 정보 수정 제안 완료 화면 **/
        data object Complete: ROUTE()
    }

    data object TypeTest {
        @Serializable
        /** 타입 테스트 진행 화면 **/
        data class Testing(val isFirst: Boolean = false): ROUTE()

        @Serializable
        /** 타입 테스트 종료 화면 **/
        data class TestEnd(
            val isFirst: Boolean,
            val travelType: TravelType
        ): ROUTE()

        @Serializable
        /** 타입 결과 보여주기 전 화면 **/
        data class Loading(
            val isFirst: Boolean,
            val travelType: TravelType
        ): ROUTE()

        @Serializable
        /** 타입 설명 화면 **/
        data class Result(
            val name: String,
            val isMine: Boolean,
            val isFirst: Boolean,
            val travelType: TravelType? = null,
        ): ROUTE()
    }

}