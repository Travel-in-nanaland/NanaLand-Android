package com.nanaland.ui.component.main.searching

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.ui.component.main.searching.parts.SearchingScreenDeleteAllRecentSearchText
import com.nanaland.ui.component.main.searching.parts.SearchingScreenRecentSearchText

@Composable
fun SearchingScreenRecentSearchContent() {
    Column {
        Row{
            SearchingScreenRecentSearchText()

            Spacer(Modifier.weight(1f))

            SearchingScreenDeleteAllRecentSearchText {

            }
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {

        }
    }
}