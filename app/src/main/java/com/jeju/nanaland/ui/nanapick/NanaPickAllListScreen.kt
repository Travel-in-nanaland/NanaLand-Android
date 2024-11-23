package com.jeju.nanaland.ui.nanapick

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.nanapick.component.NanaPickThumbnailBanner2
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState

@Composable
fun NanaPickAllListScreen(
    moveToBackScreen: () -> Unit,
    moveToNanaPickContentScreen: (Int) -> Unit,
    viewModel: NanaPickListViewModel = hiltViewModel()
) {
    val nanaPickList = viewModel.nanaPickList.collectAsState().value
    NanaPickAllListScreen(
        nanaPickList = nanaPickList,
        getNanaPickList = viewModel::getNanaPickList,
        moveToBackScreen = moveToBackScreen,
        moveToNanaPickContentScreen = moveToNanaPickContentScreen,
        isContent = true
    )
}

@Composable
private fun NanaPickAllListScreen(
    nanaPickList: UiState<List<NanaPickBannerData>>,
    getNanaPickList: () -> Unit,
    moveToBackScreen: () -> Unit,
    moveToNanaPickContentScreen: (Int) -> Unit,
    isContent: Boolean
) {
    val lazyGridState = rememberLazyGridState()
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = lazyGridState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            lastVisibleItemIndex > (totalItemsNumber - PAGING_THRESHOLD)
        }
    }

    LaunchedEffect(loadMore.value) {
        if (loadMore.value) {
            getNanaPickList()
        }
    }
    CustomSurface {
        CustomTopBar(
            title = getString(R.string.common_나나s_Pick),
            onBackButtonClicked = moveToBackScreen
        )

        when (nanaPickList) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = 36.dp),
                    state = lazyGridState,
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalArrangement = Arrangement.spacedBy(48.dp)
                ) {
                    item {
                        Spacer(Modifier.height((40 /*피그마 패딩*/ - 24 /*아이템 간격*/).dp))
                    }
                    item { }

                    items(nanaPickList.data) { item ->
                        NanaPickThumbnailBanner2(
                            item = item,
                            onClick = moveToNanaPickContentScreen
                        )
                    }

                    item {
                        Spacer(Modifier.height((40 - 24).dp))
                    }
                    item { }
                }
            }
            is UiState.Failure -> {}
        }
    }
}
