package com.jeju.nanaland.ui.searchInContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.text.TextWithPointColor
import com.jeju.nanaland.ui.component.main.searchresult.SearchResultScreenSearchResult
import com.jeju.nanaland.ui.searchInContent.component.SearchInContentSearchBar
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun SearchInContentScreen(
    moveToSignInScreen: () -> Unit,
    moveToBackScreen: () -> Unit,
    moveToCategoryContentScreen: (Int, String?, Boolean) -> Unit,
    viewModel: SearchInContentViewModel = hiltViewModel()
) {
    var isResultPage by remember { mutableStateOf(false) }
    val inputText by viewModel.inputText.collectAsState()
    val result by viewModel.categorizedSearchResultList.collectAsState()
    val recentSearchList by viewModel.recentSearchList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        SearchInContentSearchBar(
            text = inputText,
            onText = viewModel::updateInputText,
            onSearch = {
                isResultPage = true
                viewModel.onSearch(it)
            },
            moveToBackScreen = {
                if(!isResultPage)
                    moveToBackScreen()
                else
                    isResultPage = false
            }
        )

        Spacer(Modifier.height(16.dp))

        if(isResultPage)
            SearchResultScreenSearchResult(
                selectedCategory = viewModel.getSearchCategoryType(),
                allSearchResultList = UiState.Loading,
                categorizedSearchResultList = result,
                getSearchResult = { viewModel.getSearchResult(inputText) },
                updateSearchCategoryType = { },
                toggleAllSearchResultFavorite = { _, _ -> },
                toggleSearchResultFavorite = viewModel::toggleSearchResultFavorite,
                onPostClick = moveToCategoryContentScreen,
                moveToSignInScreen = moveToSignInScreen,
            )
        else
            RecentScreen(
                recentSearchList = recentSearchList,
                onSearch = viewModel::selectRecentSearch,
                onRemove = viewModel::removeRecentSearch,
                onRemoveAll = viewModel::removeRecentSearchAll
            )
    }
}



@Composable
private fun ColumnScope.RecentScreen(
    recentSearchList: List<String>,
    onSearch: (String) -> Unit,
    onRemove: (String) -> Unit,
    onRemoveAll: () -> Unit,
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = getString(id = R.string.searching_screen_최근_검색어),
                style = bodyBold,
                color = getColor().black
            )
            Spacer(Modifier.weight(1f))
            if(recentSearchList.isNotEmpty())
                Text(
                    modifier = Modifier.clickableNoEffect(onRemoveAll),
                    text = getString(id = R.string.searching_screen_모두_지우기),
                    style = caption01,
                    color = getColor().gray01
                )
        }

        Spacer(Modifier.height(20.dp))

        recentSearchList.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextWithPointColor(
                    modifier = Modifier
                        .weight(1f)
                        .clickableNoEffect { onSearch(it) },
                    text = it,
                    style = body02,
                    color = getColor().black,
                    pointStyle = body02SemiBold
                )

                Image(
                    modifier = Modifier
                        .size(14.dp)
                        .clickableNoEffect { onRemove(it) },
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(getColor().black)
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}