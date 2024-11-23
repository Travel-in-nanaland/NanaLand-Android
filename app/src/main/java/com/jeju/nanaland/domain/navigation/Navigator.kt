package com.jeju.nanaland.domain.navigation

import android.util.Log
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator @Inject constructor() {
    private val navActions = MutableSharedFlow<Triple<
            Boolean,    // is go to backstack
            ROUTE?,     // go to route
            NavOptions // option
            >> (
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val navAction = navActions.asSharedFlow()

    fun tryEmit(
        isPopBackStack: Boolean,
        route: ROUTE?,
        navOptions: NavOptions
    ) {
        navActions.tryEmit(Triple(isPopBackStack, route, navOptions))
    }

    fun runnableNavigate(
        navController: NavHostController,
        action: Triple<Boolean, ROUTE?, NavOptions>
    ) {
        Log.d("asd","@@@@@$action")
        if(action.first){
            if(navController.previousBackStackEntry != null) {
                if(action.second != null)
                    navController.popBackStack(action.second!!, action.third.isPopUpToInclusive())
                else
                    navController.popBackStack()
            }
        } else {
            if(action.second != null) {
                navController.navigate(action.second!!, action.third)
            }
        }
    }
}

