package com.jeju.nanaland.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
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
import com.jeju.nanaland.ui.navigation.nanapickAllListScreen
import com.jeju.nanaland.ui.navigation.natureContentScreen
import com.jeju.nanaland.ui.navigation.natureListScreen
import com.jeju.nanaland.ui.navigation.permissionCheckingRoute
import com.jeju.nanaland.ui.navigation.policyAgreeScreen
import com.jeju.nanaland.ui.navigation.policySettingScreen
import com.jeju.nanaland.ui.navigation.privacyPolicyDetailsScreen
import com.jeju.nanaland.ui.navigation.recommendedSpotScreen
import com.jeju.nanaland.ui.navigation.reportScreen
import com.jeju.nanaland.ui.navigation.restaurantContentScreen
import com.jeju.nanaland.ui.navigation.restaurantListScreen
import com.jeju.nanaland.ui.navigation.reviewListScreen
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
import com.jeju.nanaland.ui.noticeDetail.noticeDetailScreen
import com.jeju.nanaland.ui.notification.notificationScreen
import com.jeju.nanaland.ui.profile.profileScreenRoute
import com.jeju.nanaland.ui.searchInContent.searchInContentRoute
import com.jeju.nanaland.util.intent.DeepLinkData

@Composable
fun MainNavigation(
    deepLinkData: DeepLinkData,
    navController: NavHostController,
    navViewModel: NavViewModel
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = ROUTE.Splash
    ) {

        // 스플래시 화면
        splashScreen(
            deepLinkData = deepLinkData,
            navViewModel = navViewModel
        )

        // 언어 선택 화면
        languageInitializationScreen(navViewModel)

        // 로그인 화면
        signInScreen(
            deepLinkData = deepLinkData,
            navViewModel = navViewModel
        )

        // 약관 동의 화면
        policyAgreeScreen(navViewModel)

        // 이용약관 동의 및 개인정보 처리방침 화면
        privacyPolicyDetailsScreen()

        // 마케팅 활용 동의 화면
        marketingPolicyDetailsScreen()

        // 위치기반 서비스 약관 동의 화면
        locationPolicyDetailsScreen()

        // 회원가입 프로필 설정 화면
        signUpScreen(navViewModel)

        // 유형 테스트 화면
        typeTestingScreen(navViewModel)

        // 유형 테스트 완료 화면
        typeTestCompletionScreen(navViewModel)

        // 유형 테스트 로딩 화면
        typeTestLoadingScreen(navViewModel)

        // 유형 테스트 결과 화면
        typeTestResultScreen(navViewModel)

        // 유형 테스트별 추천 여행지 화면
        recommendedSpotScreen(navViewModel)

        // 메인 화면
        mainScreen(
            deepLinkData = deepLinkData,
            navViewModel = navViewModel
        )

        // 알림 화면
        notificationScreen(navViewModel)

        // 자연 리스트 화면
        natureListScreen(navViewModel)

        // 자연 상세 화면
        natureContentScreen(navViewModel) {
            navController.previousBackStackEntry!!
        }

        // 축제 리스트 화면
        festivalListScreen(navViewModel)

        // 축제 상세 화면
        festivalContentScreen(navViewModel) {
            navController.previousBackStackEntry!!
        }

        // 전통시장 리스트 화면
        marketListScreen(navViewModel)

        // 전통시장 상세 화면
        marketContentScreen(navViewModel) {
            navController.previousBackStackEntry!!
        }

        // 이색체험 리스트 화면
        experienceListScreen(navViewModel)

        // 이색체험 상세 화면
        experienceContentScreen(navViewModel) {
            navController.previousBackStackEntry!!
        }

        // 나나픽 전체 리스트 화면
        nanapickAllListScreen(navViewModel)

        // 나나 Pick 상세 화면
        nanaPickContentScreen(navViewModel)

        // 제주 맛집 리스트 화면
        restaurantListScreen(navViewModel)

        // 제주 맛집 상세 화면
        restaurantContentScreen(navViewModel) {
            navController.previousBackStackEntry!!
        }

        // 정보 수정 제안 카테고리 선택 화면
        informationModificationProposalCategoryScreen(navViewModel)

        // 정보 수정 제안 작성 화면
        informationModificationProposalWritingScreen(navViewModel)

        // 정보 수정 제안 완료 화면
        informationModificationProposalCompleteScreen(navViewModel)

        // 설정 화면
        settingsScreen(navViewModel)

        // 약관 및 정책 설정 화면
        policySettingScreen(navViewModel)

        // 접근 권한 안내 화면
        permissionCheckingRoute(navViewModel)

        // 회원 탈퇴 화면
        withdrawalScreen(navViewModel)

        // 언어 변경 화면
        languageChangeScreen(navViewModel)

        // 후기 작성 화면
        reviewWriteRoute(navViewModel, {
            navController.getBackStackEntry(it)
        }, {
            navController.previousBackStackEntry
        })

        // 타인 프로필 화면
        profileScreenRoute(navViewModel) {
            navController.previousBackStackEntry!!
        }

        // 리뷰 리스트 화면
        reviewListScreen(navViewModel)

        // 신고 화면
        reportScreen(navViewModel)

        // 공지 디테일
        noticeDetailScreen(navViewModel)

        searchInContentRoute(navViewModel)
    }
}