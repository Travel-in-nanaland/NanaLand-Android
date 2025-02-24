package com.jeju.nanaland.ui.component.common.icon

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.launch

@Composable
fun GoToUpInList(
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
) = GoToUpInListWrapper(
    modifier = modifier,
    state = scrollState,
    isScrolled = { it.canScrollBackward },
    scrollToTop = { it.animateScrollTo(0) }
)

@Composable
fun GoToUpInList(
    scrollState: LazyGridState,
    modifier: Modifier = Modifier,
) = GoToUpInListWrapper(
    modifier = modifier,
    state = scrollState,
    isScrolled = { it.firstVisibleItemIndex > 0 || it.firstVisibleItemScrollOffset > 0 },
    scrollToTop = { it.animateScrollToItem(0) }
)

@Composable
fun GoToUpInList(
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
) = GoToUpInListWrapper(
    modifier = modifier,
    state = scrollState,
    isScrolled = { it.firstVisibleItemIndex > 0 || it.firstVisibleItemScrollOffset > 0 },
    scrollToTop = { it.animateScrollToItem(0) }
)



@Composable
private fun <T> GoToUpInListWrapper(
    modifier: Modifier,
    state: T,
    isScrolled: (T) -> Boolean,
    scrollToTop: suspend (T) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val rememberIsScrolled by remember {
        derivedStateOf {
            isScrolled(state)
        }
    }

    AnimatedVisibility(
        modifier = modifier.padding(bottom = 24.dp, end = 16.dp),
        visible = rememberIsScrolled,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Icon(
            modifier = Modifier
                .size(36.dp)
                .background(getColor().main90, CircleShape)
                .clickableNoEffect {
                    coroutineScope.launch {
                        scrollToTop(state)
                    }
                }
                .padding(6.dp),
            painter = painterResource(R.drawable.ic_arrow_up),
            contentDescription = null,
            tint = getColor().white,
        )
    }
}