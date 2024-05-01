package com.nanaland.ui.main.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.globalvalue.constant.TOP_BAR_HEIGHT
import com.nanaland.globalvalue.type.HomeScreenViewType
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.body02
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.CustomPreview
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun HomeScreenTopBar(
    inputText: String,
    onValueChange: (String) -> Unit,
    currentViewType: HomeScreenViewType,
    updateViewType: (HomeScreenViewType) -> Unit,
    getSearchResult: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    var isFocused by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .height(TOP_BAR_HEIGHT.dp)
            .background(getColor().white),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clickableNoEffect {
                    if (currentViewType != HomeScreenViewType.Home) {
                        onValueChange("")
                        focusManager.clearFocus()
                        updateViewType(HomeScreenViewType.Home)
                    }
                }
                .padding(start = 16.dp, end = 8.dp)
                .size(32.dp),
            painter = painterResource(id = if (currentViewType == HomeScreenViewType.Home) R.drawable.ic_logo else R.drawable.ic_back),
            contentDescription = null
        )
        BasicTextField(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isFocused = it.isFocused
                    if (currentViewType == HomeScreenViewType.Home && it.isFocused) {
                        updateViewType(HomeScreenViewType.Searching)
                    }
                },
            value = inputText,
            onValueChange = {
                onValueChange(it)
            },
            singleLine = true,
            textStyle = body02,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (inputText != "") {
                        updateViewType(HomeScreenViewType.SearchResult)
                        getSearchResult(inputText)
                    }
                }
            )
        ) {
            Box(
                modifier = Modifier
                    .height(TOP_BAR_HEIGHT.dp)
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = getColor().main
                        ),
                        shape = RoundedCornerShape(50)
                    )
                    .padding(start = 20.dp, end = 20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                it()
                if (inputText == "") {
                    if (isFocused) {
                        Text(
                            text = "검색어를 입력해 주세요",
                            color = getColor().gray01,
                            style = body02
                        )
                    } else {
                        Text(
                            text = "제주도는 지금 유채꽃 축제",
                            color = getColor().gray01,
                            style = body02
                        )
                    }
                }
            }
        }
        Image(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .size(20.dp),
            painter = painterResource(id = R.drawable.ic_alarm),
            contentDescription = null
        )
    }
}

@CustomPreview
@Composable
private fun HomeScreenTopBarPreview_Home() {
    NanaLandTheme {
        HomeScreenTopBar(
            inputText = "",
            onValueChange = {},
            currentViewType = HomeScreenViewType.Home,
            updateViewType = {},
            getSearchResult = {}
        )
    }
}

@CustomPreview
@Composable
private fun HomeScreenTopBarPreview_Searching() {
    NanaLandTheme {
        HomeScreenTopBar(
            inputText = "",
            onValueChange = {},
            currentViewType = HomeScreenViewType.Searching,
            updateViewType = {},
            getSearchResult = {}
        )
    }
}

@CustomPreview
@Composable
private fun HomeScreenTopBarPreview_SearchResult() {
    NanaLandTheme {
        HomeScreenTopBar(
            inputText = "",
            onValueChange = {},
            currentViewType = HomeScreenViewType.SearchResult,
            updateViewType = {},
            getSearchResult = {}
        )
    }
}