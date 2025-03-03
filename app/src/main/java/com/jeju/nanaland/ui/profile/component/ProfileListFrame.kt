package com.jeju.nanaland.ui.profile.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.launch

@Composable
fun <T : Any> ProfileListFrame(
    title: String,
    initialScrollToItem: Int,
    moveToBackScreen: () -> Unit,
    data: LazyPagingItems<T>,
    emptyView: @Composable () -> Unit = {},
    errorView: @Composable () -> Unit = {},
    rowView: @Composable (T) -> Unit
) {
    val scope = rememberCoroutineScope()
    val state = rememberLazyListState()
    val isVisibleFAB by remember {
        derivedStateOf {
            state.firstVisibleItemIndex != 0
        }
    }

    CustomSurface { isImeKeyboardShowing ->
        Scaffold(
            floatingActionButton = {
                FAB(isVisibleFAB) {
                    scope.launch {
                        state.animateScrollToItem(0)
                    }
                }
            },
            containerColor = getColor().surface,
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .imePadding()
                    .padding(bottom = if (isImeKeyboardShowing) 0.dp else it.calculateBottomPadding())
            ) {
                TopBarCommon(
                    title = title,
                    onBackButtonClicked = moveToBackScreen
                )
                when(data.loadState.refresh) {
                    is LoadState.NotLoading -> {
                        if(data.itemCount == 0) {
                            emptyView()
                        } else {
                            LazyColumn(
                                Modifier
                                    .weight(1f)
                                    .padding(horizontal = 16.dp),
                                state = state,
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                item { Spacer(Modifier.height((24 - 16).dp)) }

                                items(data.itemCount) { index ->
                                    rowView(data[index]!!)
                                }

                                item { Spacer(Modifier.height((24 - 16).dp)) }
                            }
                        }
                    }
                    is LoadState.Loading -> {
                        Box(Modifier.fillMaxSize()) {
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    }
                    is LoadState.Error -> {
                        errorView()
                    }
                }
            }
        }
    }

    LaunchedEffect(initialScrollToItem) {
        Thread.sleep(200)
        if(0 < initialScrollToItem)
            state.animateScrollToItem(1 + initialScrollToItem)
    }
}

@Composable
private fun FAB(
    isVisible: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(getColor().main90)
                .clickableNoEffect { onClick() }
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.ic_arrow_up),
                contentDescription = null,
                tint = getColor().white
            )
        }
    }
}