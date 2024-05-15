package com.jeju.nanaland.util.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

@SuppressLint("RestrictedApi")
fun NavController.navigate(
    route: String,
    args: Bundle,
    isSingleTop: Boolean = true,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    if (isSingleTop) {
        if (this.currentBackStackEntry?.destination?.route != null) {
            if (this.currentBackStackEntry!!.destination.route == route) return
        }
    }
    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(NavDestination.createRoute(route).toUri())
        .build()

    val deepLinkMatch = graph.matchDeepLink(routeLink)
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val id = destination.id
        Log.e("", "${args}")
        navigate(id, args, navOptions, navigatorExtras)
    } else {
        navigate(route, navOptions, navigatorExtras)
    }
}