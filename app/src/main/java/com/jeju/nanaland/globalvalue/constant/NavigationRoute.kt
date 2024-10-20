package com.jeju.nanaland.globalvalue.constant

import kotlinx.serialization.Serializable

const val ROUTE_SPLASH = "ROUTE_SPLASH"
const val ROUTE_LANGUAGE_INITIALIZATION = "ROUTE_LANGUAGE_INITIALIZATION"
const val ROUTE_SIGN_IN = "ROUTE_SIGN_IN"
const val ROUTE_MAIN = "ROUTE_MAIN"
const val ROUTE_RESTAURANT_LIST = "ROUTE_RESTAURANT_LIST"
const val ROUTE_RESTAURANT_CONTENT = "ROUTE_RESTAURANT_CONTENT"
const val ROUTE_NANAPICK_LIST = "ROUTE_NANAPICK_LIST"
const val ROUTE_NANAPICK_CONTENT = "ROUTE_NANAPICK_CONTENT"
const val ROUTE_NATURE_LIST = "ROUTE_NATURE_LIST"
const val ROUTE_NATURE_CONTENT = "ROUTE_NATURE_CONTENT"
const val ROUTE_FESTIVAL_LIST = "ROUTE_FESTIVAL_LIST"
const val ROUTE_FESTIVAL_CONTENT = "ROUTE_FESTIVAL_CONTENT"
const val ROUTE_MARKET_LIST = "ROUTE_MARKET_LIST"
const val ROUTE_MARKET_CONTENT = "ROUTE_MARKET_CONTENT"
const val ROUTE_EXPERIENCE_LIST = "ROUTE_EXPERIENCE_LIST"
const val ROUTE_EXPERIENCE_CONTENT = "ROUTE_EXPERIENCE_CONTENT"
const val ROUTE_INFORMATION_MODIFICATION_PROPOSAL_CATEGORY = "ROUTE_INFORMATION_MODIFICATION_PROPOSAL_CATEGORY"
const val ROUTE_INFORMATION_MODIFICATION_PROPOSAL_WRITING = "ROUTE_INFORMATION_MODIFICATION_PROPOSAL_WRITING"
const val ROUTE_INFORMATION_MODIFICATION_PROPOSAL_COMPLETE = "ROUTE_INFORMATION_MODIFICATION_PROPOSAL_COMPLETE"
const val ROUTE_POLICY_AGREE = "ROUTE_POLICY_AGREE"
const val ROUTE_SIGN_UP = "ROUTE_SIGN_UP"
const val ROUTE_PRIVACY_POLICY_DETAILS = "ROUTE_PRIVACY_POLICY_DETAILS"
const val ROUTE_MARKETING_POLICY_DETAILS = "ROUTE_MARKETING_POLICY_DETAILS"
const val ROUTE_LOCATION_POLICY_DETAILS = "ROUTE_LOCATION_POLICY_DETAILS"
//const val ROUTE_TYPE_TESTING = "ROUTE_TYPE_TESTING"
//const val ROUTE_TYPE_TEST_COMPLETION = "ROUTE_TYPE_TEST_COMPLETION"
//const val ROUTE_TYPE_TEST_LOADING = "ROUTE_TYPE_TEST_LOADING"
//const val ROUTE_TYPE_TEST_RESULT = "ROUTE_TYPE_TEST_RESULT"
//const val ROUTE_PROFILE = "ROUTE_PROFILE"
//const val ROUTE_PROFILE_UPDATE = "ROUTE_PROFILE_MODIFICATION"
const val ROUTE_SETTINGS = "ROUTE_SETTINGS"
const val ROUTE_POLICY_SETTING = "ROUTE_POLICY_SETTING"
const val ROUTE_PERMISSION_CHECKING = "ROUTE_PERMISSION_CHECKING"
const val ROUTE_WITHDRAW = "ROUTE_WITHDRAW"
const val ROUTE_LANGUAGE_CHANGE = "ROUTE_LANGUAGE_CHANGE"
const val ROUTE_RECOMMENDED_SPOT = "ROUTE_RECOMMENDED_SPOT"
//const val ROUTE_NOTIFICATION = "ROUTE_NOTIFICATION"
const val ROUTE_REVIEW_WRITE_ROUTE = "ROUTE_REVIEW_WRITE_ROUTE"
const val ROUTE_REVIEW_WRITE_SEARCH = "ROUTE_REVIEW_WRITE_SEARCH"
const val ROUTE_REVIEW_WRITE = "ROUTE_REVIEW_WRITE"
const val ROUTE_REVIEW_WRITE_KEYWORD = "ROUTE_REVIEW_WRITE_KEYWORD"
const val ROUTE_REVIEW_WRITE_COMPLETE = "ROUTE_REVIEW_WRITE_COMPLETE"
//const val ROUTE_PROFILE_NOTICE = "ROUTE_PROFILE_NOTICE"
//const val ROUTE_PROFILE_REVIEW = "ROUTE_PROFILE_REVIEW"
const val ROUTE_REVIEW_LIST = "ROUTE_REVIEW_LIST"
//const val ROUTE_REPORT = "ROUTE_REPORT"
//const val ROUTE_BOARD = "ROUTE_BOARD"
const val ROUTE_NANAPICK_ALL_LIST = "ROUTE_NANAPICK_ALL_LIST"



sealed class ROUTE {
    @Serializable
    data class NoticeDetail(val noticeId: Int)

    @Serializable
    data class Report(val reportId: Int, val isReview: Boolean)


    @Serializable
    data object Home {
        @Serializable
        data object StartDest

        @Serializable
        data object Notification
    }

    @Serializable
    data object Profile {
        @Serializable
        data class StartDest(val userId: Int? = null)

        @Serializable /** 프로필 수정 화면 **/
        data class Update(
            val profileImageUri: String,
            val nickname: String,
            val introduction: String
        )

        @Serializable /** 프로필 공지사항 리스트 화면 **/
        data object NoticeList

        @Serializable /** 프로필 리뷰 리스트 화면 **/
        data class ReviewList(val initialScrollToItemId: Int?)
    }

    data object TypeTest {
        @Serializable /** 타입 테스트 진행 화면 **/
        data class Testing(val isFirst: Boolean = false)

        @Serializable /** 타입 테스트 종료 화면 **/
        data class TestEnd(
            val isFirst: Boolean,
            val travelType: TravelType
        )

        @Serializable /** 타입 결과 보여주기 전 화면 **/
        data class Loading(
            val isFirst: Boolean,
            val travelType: TravelType
        )

        @Serializable /** 타입 설명 화면 **/
        data class Result(
            val name: String,
            val isMine: Boolean,
            val isFirst: Boolean,
            val travelType: TravelType? = null,
        )
    }

}
