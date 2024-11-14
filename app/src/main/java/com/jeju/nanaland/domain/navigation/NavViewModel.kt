package com.jeju.nanaland.domain.navigation

import androidx.lifecycle.ViewModel
import androidx.navigation.navOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel() {
//    private val defaultOption = NavOptions.Builder()
//        .setLaunchSingleTop(true)
//        .build()

    fun popBackStack() {
        navigator.tryEmit(true, null, navOptions {  })
    }
    fun popBackStack(
        popUpToRoute: ROUTE,
        isInclusive: Boolean
    ) {
        navigator.tryEmit(true, popUpToRoute, navOptions {
            popUpTo(popUpToRoute) {
                inclusive = isInclusive
            }
        })
    }

    fun navigate(
        route: ROUTE,
    ) {
        navigator.tryEmit(false, route, navOptions {
            launchSingleTop = true
        })
    }
    fun navigatePopUpTo(
        destRoute: ROUTE,
        popUpToRoute: ROUTE,
        isInclusive: Boolean = true
    ) {
        navigator.tryEmit(false, destRoute, navOptions {
            popUpTo(popUpToRoute) {
                inclusive = isInclusive
            }
            launchSingleTop = true
        })
    }

}
