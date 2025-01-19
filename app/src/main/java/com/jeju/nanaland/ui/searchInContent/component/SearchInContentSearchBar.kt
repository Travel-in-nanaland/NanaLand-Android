package com.jeju.nanaland.ui.searchInContent.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.searchText
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun SearchInContentSearchBar(
    text: String,
    onText: (String)-> Unit,
    onSearch: (String) -> Unit,
    moveToBackScreen: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp)
                .clickableNoEffect{
                    focusRequester.requestFocus()
                    moveToBackScreen()
            },
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = null,
            tint = getColor().black
        )

        Spacer(Modifier.width(16.dp))

        BasicTextField(
            modifier = Modifier
                .focusRequester(focusRequester)
                .weight(1f)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = getColor().main
                    ),
                    shape = RoundedCornerShape(50)
                )
                .padding(horizontal = 12.dp, vertical = (12.5).dp),
            value = text,
            onValueChange = onText,
            singleLine = true,
            textStyle = searchText,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (text.isNotEmpty()) {
                        onSearch(text)
                        focusManager.clearFocus()
                    }
                }
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.weight(1f),contentAlignment = Alignment.CenterStart) {
                    it()
                    if(text.isEmpty()) {
                        Text(
                            text = getString(id = R.string.searching_screen_textfiled_hint),
                            style = searchText,
                            color = getColor().gray01
                        )
                    }
                }
                Image(
                    modifier = Modifier
                        .size(12.dp)
                        .clickableNoEffect {
                            onText("")
                            focusRequester.requestFocus()
                        },
                    painter = painterResource(R.drawable.ic_close_circled),
                    contentDescription = null
                )
            }
        }
    }
}
