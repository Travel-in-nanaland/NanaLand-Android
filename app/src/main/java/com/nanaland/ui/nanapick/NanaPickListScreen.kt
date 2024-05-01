package com.nanaland.ui.nanapick

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
import com.nanaland.domain.entity.nanapick.NanaPickThumbnail
import com.nanaland.ui.component.CustomSurface
import com.nanaland.ui.component.CustomTopBar
import com.nanaland.util.ui.UiState

@Composable
fun NanaPickListScreen(
    moveToNanaPickContentScreen: (Long) -> Unit,
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
    nanaPickList: UiState<List<NanaPickThumbnail>>,
    moveToMainScreen: () -> Unit,
    moveToNanaPickContentScreen: (Long) -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        Column() {
            CustomTopBar(
                title = "나나's Pick",
                onBackButtonClicked = moveToMainScreen
            )
            LazyColumn {
                when (nanaPickList) {
                    is UiState.Empty -> {}
                    is UiState.Loading -> {

                    }
                    is UiState.Success -> {
                        itemsIndexed(nanaPickList.data) { idx, item ->
                            NanaPickThumbnail(
                                item = item,
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