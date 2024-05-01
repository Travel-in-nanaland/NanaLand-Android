package com.nanaland.ui.main.home.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.R
import com.nanaland.domain.entity.search.SearchResult
import com.nanaland.globalvalue.type.SearchCategoryType
import com.nanaland.ui.component.SearchContentCard
import com.nanaland.ui.main.home.HomeViewModel
import com.nanaland.ui.theme.body02
import com.nanaland.ui.theme.caption01
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.title02Bold
import com.nanaland.util.resource.getString
import com.nanaland.util.ui.UiState
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun SearchResultContent(
    scaffoldPadding: PaddingValues,
    searchViewModel: SearchViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val inputText = homeViewModel.inputText.collectAsState().value
    val selectedCategory = searchViewModel.selectedCategory.collectAsState().value
    val allSearchResultList = searchViewModel.allSearchResultList.collectAsState().value
    val categorizedSearchResultList = searchViewModel.categorizedSearchResultList.collectAsState().value
    SearchResultContent(
        scaffoldPadding = scaffoldPadding,
        inputText = inputText,
        getSearchResult = searchViewModel::getSearchResult,
        selectedCategory = selectedCategory,
        allSearchResultList = allSearchResultList,
        categorizedSearchResultList = categorizedSearchResultList,
        updateSelectedCategory = searchViewModel::updateSelectedCategory,
        isContent = true
    )
}

@Composable
private fun SearchResultContent(
    scaffoldPadding: PaddingValues,
    inputText: String,
    getSearchResult: (String) -> Unit,
    selectedCategory: SearchCategoryType,
    allSearchResultList: UiState<Map<String, SearchResult>>,
    categorizedSearchResultList: UiState<SearchResult>,
    updateSelectedCategory: (SearchCategoryType) -> Unit,
    isContent: Boolean
) {
    CategoryListTab(
        inputText = inputText,
        getSearchResult = getSearchResult,
        selectedCategory = selectedCategory,
        updateSelectedCategory = updateSelectedCategory
    )

    Spacer(Modifier.height(30.dp))

    SearchResultList(
        scaffoldPadding = scaffoldPadding,
        selectedCategory = selectedCategory,
        allSearchResultList = allSearchResultList,
        categorizedSearchResultList = categorizedSearchResultList
    )

    Spacer(Modifier.height(scaffoldPadding.calculateBottomPadding()))
}

@Composable
private fun CategoryListTab(
    inputText: String,
    getSearchResult: (String) -> Unit,
    selectedCategory: SearchCategoryType,
    updateSelectedCategory: (SearchCategoryType) -> Unit,
) {
    val mainColor = getColor().main
    LazyRow {
        itemsIndexed(SearchCategoryType.entries.toTypedArray()) { idx, item ->
            Row() {
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .drawBehind {
                            if (selectedCategory == item) {
                                val borderSize = 2.dp.toPx()
                                drawLine(
                                    color = mainColor,
                                    start = Offset(0f, size.height),
                                    end = Offset(size.width, size.height),
                                    strokeWidth = borderSize
                                )
                            }
                        }
                        .clickableNoEffect {
                            updateSelectedCategory(item)
                            getSearchResult(inputText)
                        }
                        .padding(start = 16.dp, end = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = getString(item.title),
                        color = getColor().black,
                        style = caption01
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchResultList(
    scaffoldPadding: PaddingValues,
    selectedCategory: SearchCategoryType,
    allSearchResultList: UiState<Map<String, SearchResult>>,
    categorizedSearchResultList: UiState<SearchResult>,
) {
    if (selectedCategory == SearchCategoryType.All) {
        LazyColumn(
            modifier = Modifier.imePadding()
        ) {
            item {
                SearchCategoryType.entries.forEach {
                    if (it != SearchCategoryType.All) {
                        SearchResultPreview(
                            category = it,
                            allSearchResultList = allSearchResultList
                        )

                        Spacer(Modifier.height(40.dp))
                    }
                }

                Spacer(Modifier.height(scaffoldPadding.calculateBottomPadding()))
            }
        }
    } else {
        SearchedResultByCategoryContent(
            scaffoldPadding = scaffoldPadding,
            categorizedSearchResultList = categorizedSearchResultList
        )
    }
}

@Composable
private fun SearchResultPreview(
    category: SearchCategoryType,
    allSearchResultList: UiState<Map<String, SearchResult>>,
) {
    when (allSearchResultList) {
        is UiState.Empty -> {}
        is UiState.Loading -> {}
        is UiState.Success -> {
            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = getString(category.title),
                    color = getColor().black,
                    style = title02Bold
                )

                Spacer(Modifier.width(10.dp))

                Text(
                    text = "${allSearchResultList.data[category.name]?.count ?: "0"}건",
                    color = getColor().gray01,
                    style = body02
                )

                Spacer(Modifier.weight(1f))

                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = null
                )
            }

            Spacer(Modifier.height(10.dp))

            Spacer(
                Modifier
                    .padding(start = 16.dp, end= 16.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(getColor().gray02)
            )

            Spacer(Modifier.height(10.dp))

            allSearchResultList.data[category.name]?.let { searchResult ->
                if (searchResult.data.isEmpty()) {
                    Spacer(Modifier.height(20.dp))

                    EmptySearchResultContent()

                    Spacer(Modifier.height(20.dp))
                } else {
                    Row(
                        modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                    ) {
                        repeat(searchResult.data.size) {
                            Box(
                                modifier = Modifier
                                    .padding(start = 4.dp, end = 4.dp)
                            ) {
                                SearchContentCard(
                                    title = searchResult.data[it].title,
                                    imageUrl = searchResult.data[it].thumbnailUrl
                                )
                            }
                        }
                    }
                }
            }
        }
        is UiState.Failure -> {}
    }
}

@Composable
private fun SearchedResultByCategoryContent(
    scaffoldPadding: PaddingValues,
    categorizedSearchResultList: UiState<SearchResult>,
) {
    when (categorizedSearchResultList) {
        is UiState.Empty -> {}
        is UiState.Loading -> {}
        is UiState.Success -> {
            LazyVerticalGrid(
                contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
                columns = GridCells.Fixed(2)
            ) {
                itemsIndexed(categorizedSearchResultList.data.data) { idx, item ->
                    Log.e("", "${idx}")
                    Box(
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                    ) {
                        SearchContentCard(
                            title = item.title,
                            imageUrl = item.thumbnailUrl
                        )
                    }
                }
                item {
                    Spacer(Modifier.height(scaffoldPadding.calculateBottomPadding()))
                }
                item {
                    Spacer(Modifier.height(scaffoldPadding.calculateBottomPadding()))
                }
            }
        }
        is UiState.Failure -> {}
    }
}

@Composable
private fun EmptySearchResultContent() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(48.dp),
            painter = painterResource(id = R.drawable.ic_orange_face),
            contentDescription = null
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "해당 검색 결과가 없습니다.",
            color = getColor().gray01,
            style = body02
        )
    }
}

@Preview
@Composable
private fun SearchResultContentPreview() {

}