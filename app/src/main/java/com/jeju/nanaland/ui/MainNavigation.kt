package com.jeju.nanaland.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jeju.nanaland.globalvalue.constant.ROUTE_SPLASH
import com.jeju.nanaland.ui.navigation.experienceContentScreen
import com.jeju.nanaland.ui.navigation.experienceListScreen
import com.jeju.nanaland.ui.navigation.festivalContentScreen
import com.jeju.nanaland.ui.navigation.festivalListScreen
import com.jeju.nanaland.ui.navigation.informationModificationProposalCategoryScreen
import com.jeju.nanaland.ui.navigation.informationModificationProposalCompleteScreen
import com.jeju.nanaland.ui.navigation.informationModificationProposalWritingScreen
import com.jeju.nanaland.ui.navigation.languageChangeScreen
import com.jeju.nanaland.ui.navigation.languageInitializationScreen
import com.jeju.nanaland.ui.navigation.locationPolicyDetailsScreen
import com.jeju.nanaland.ui.navigation.mainScreen
import com.jeju.nanaland.ui.navigation.marketContentScreen
import com.jeju.nanaland.ui.navigation.marketListScreen
import com.jeju.nanaland.ui.navigation.marketingPolicyDetailsScreen
import com.jeju.nanaland.ui.navigation.nanaPickContentScreen
import com.jeju.nanaland.ui.navigation.nanaPickListScreen
import com.jeju.nanaland.ui.navigation.natureContentScreen
import com.jeju.nanaland.ui.navigation.natureListScreen
import com.jeju.nanaland.ui.navigation.notificationScreen
import com.jeju.nanaland.ui.navigation.permissionCheckingRoute
import com.jeju.nanaland.ui.navigation.policyAgreeScreen
import com.jeju.nanaland.ui.navigation.policySettingScreen
import com.jeju.nanaland.ui.navigation.privacyPolicyDetailsScreen
import com.jeju.nanaland.ui.navigation.profileNoticeScreenRoute
import com.jeju.nanaland.ui.navigation.profileReviewScreenRoute
import com.jeju.nanaland.ui.navigation.profileScreenRoute
import com.jeju.nanaland.ui.navigation.profileUpdateScreen
import com.jeju.nanaland.ui.navigation.recommendedSpotScreen
import com.jeju.nanaland.ui.navigation.restaurantContentScreen
import com.jeju.nanaland.ui.navigation.restaurantListScreen
import com.jeju.nanaland.ui.navigation.reviewListScreen
import com.jeju.nanaland.ui.navigation.reportScreen
import com.jeju.nanaland.ui.navigation.reviewWriteRoute
import com.jeju.nanaland.ui.navigation.settingsScreen
import com.jeju.nanaland.ui.navigation.signInScreen
import com.jeju.nanaland.ui.navigation.signUpScreen
import com.jeju.nanaland.ui.navigation.splashScreen
import com.jeju.nanaland.ui.navigation.typeTestCompletionScreen
import com.jeju.nanaland.ui.navigation.typeTestLoadingScreen
import com.jeju.nanaland.ui.navigation.typeTestResultScreen
import com.jeju.nanaland.ui.navigation.typeTestingScreen
import com.jeju.nanaland.ui.navigation.withdrawalScreen
import com.jeju.nanaland.util.intent.DeepLinkData

@Composable
fun MainNavigation(
    deepLinkData: DeepLinkData,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
//        startDestination = ROUTE_SIGN_UP,
        startDestination = ROUTE_SPLASH
    ) {

        // 스플래시 화면
        splashScreen(
            deepLinkData = deepLinkData,
            navController = navController
        )

        // 언어 선택 화면
        languageInitializationScreen(navController)

        // 로그인 화면
        signInScreen(
            deepLinkData = deepLinkData,
            navController = navController
        )

        // 약관 동의 화면
        policyAgreeScreen(navController)

        // 이용약관 동의 및 개인정보 처리방침 화면
        privacyPolicyDetailsScreen(navController)

        // 마케팅 활용 동의 화면
        marketingPolicyDetailsScreen(navController)

        // 위치기반 서비스 약관 동의 화면
        locationPolicyDetailsScreen(navController)

        // 회원가입 프로필 설정 화면
        signUpScreen(navController)

        // 유형 테스트 화면
        typeTestingScreen(navController)

        // 유형 테스트 완료 화면
        typeTestCompletionScreen(navController)

        // 유형 테스트 로딩 화면
        typeTestLoadingScreen(navController)

        // 유형 테스트 결과 화면
        typeTestResultScreen(navController)

        // 유형 테스트별 추천 여행지 화면
        recommendedSpotScreen(navController)

        // 메인 화면
        mainScreen(
            deepLinkData = deepLinkData,
            navController = navController
        )

        // 알림 화면
        notificationScreen(navController)

        // 자연 리스트 화면
        natureListScreen(navController)

        // 자연 상세 화면
        natureContentScreen(navController)

        // 축제 리스트 화면
        festivalListScreen(navController)

        // 축제 상세 화면
        festivalContentScreen(navController)

        // 전통시장 리스트 화면
        marketListScreen(navController)

        // 전통시장 상세 화면
        marketContentScreen(navController)

        // 이색체험 리스트 화면
        experienceListScreen(navController)

        // 이색체험 상세 화면
        experienceContentScreen(navController)

        // 나나 Pick 리스트 화면
        nanaPickListScreen(navController)

        // 나나 Pick 상세 화면
        nanaPickContentScreen(navController)

        // 제주 맛집 리스트 화면
        restaurantListScreen(navController)

        // 제주 맛집 상세 화면
        restaurantContentScreen(navController)

        // 정보 수정 제안 카테고리 선택 화면
        informationModificationProposalCategoryScreen(navController)

        // 정보 수정 제안 작성 화면
        informationModificationProposalWritingScreen(navController)

        // 정보 수정 제안 완료 화면
        informationModificationProposalCompleteScreen(navController)

        // 프로필 수정 화면
        profileUpdateScreen(navController)

        // 설정 화면
        settingsScreen(navController)

        // 약관 및 정책 설정 화면
        policySettingScreen(navController)

        // 접근 권한 안내 화면
        permissionCheckingRoute(navController)

        // 회원 탈퇴 화면
        withdrawalScreen(navController)

        // 언어 변경 화면
        languageChangeScreen(navController)

        // 후기 작성 화면
        reviewWriteRoute(navController)

        // 타인 프로필 화면
        profileScreenRoute(navController)

        // 프로필 공지사항 리스트 화면
        profileNoticeScreenRoute(navController)

        // 프로필 리뷰 리스트 화면
        profileReviewScreenRoute(navController)

        // 리뷰 리스트 화면
        reviewListScreen(navController)

        // 신고 화면
        reportScreen(navController)
    }
}