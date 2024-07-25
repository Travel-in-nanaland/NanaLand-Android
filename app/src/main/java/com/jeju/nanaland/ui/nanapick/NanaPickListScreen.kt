package com.jeju.nanaland.ui.nanapick

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.component.main.home.parts.HomeScreenNanaPickBanner
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState

@Composable
fun NanaPickListScreen(
    moveToNanaPickContentScreen: (Int) -> Unit,
    moveToMainScreen: () -> Unit,
    viewModel: NanaPickListViewModel = hiltViewModel()
) {
    val nanaPickList = viewModel.nanaPickList.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.getNanaPickList()
    }
    NanaPickListScreen(
        nanaPickList = nanaPickList,
        moveToNanaPickContentScreen = moveToNanaPickContentScreen,
        moveToMainScreen = moveToMainScreen,
        isContent = true
    )
}

@Composable
private fun NanaPickListScreen(
    nanaPickList: UiState<List<NanaPickBannerData>>,
    moveToMainScreen: () -> Unit,
    moveToNanaPickContentScreen: (Int) -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        Column {
            CustomTopBar(
                title = getString(R.string.common_나나s_Pick),
                onBackButtonClicked = moveToMainScreen
            )
            LazyColumn {
                when (nanaPickList) {
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        itemsIndexed(nanaPickList.data) { idx, item ->
                            HomeScreenNanaPickBanner(
                                item = item,
                                height = 200,
                                onClick = moveToNanaPickContentScreen
                            )
                            Spacer(Modifier.height(10.dp))
                        }
                    }
                    is UiState.Failure -> {}
                }
            }
        }
    }
}

@Preview
@Composable
private fun NanaPickListScreenPreview() {

}