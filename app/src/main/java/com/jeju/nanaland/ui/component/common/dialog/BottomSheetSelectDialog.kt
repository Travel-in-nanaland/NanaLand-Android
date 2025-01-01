package com.jeju.nanaland.ui.component.common.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetSelectDialog(
    state: SheetState = rememberModalBottomSheetState(true),
    onDismiss: () -> Unit,
    vararg items: Pair<String, () -> Unit>
) {
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = {
            scope.launch { state.hide() }.invokeOnCompletion {
                if (!state.isVisible) {
                    onDismiss()
                }
            }
        },
        sheetState = state,
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        containerColor = getColor().white,
        dragHandle = null,
    ) {
        NanaLandTheme {
            Column(
                modifier = Modifier
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(15.dp))

                DefaultLayout(
                    Modifier.background(getColor().white, RoundedCornerShape(12.dp))
                ) {
                    Column {
                        items.forEach {
                            DefaultTextView(it.first, it.second)
                        }
                    }
                }

                Spacer(Modifier.height(10.dp))

                DefaultLayout(
                    Modifier.background(getColor().gray03, RoundedCornerShape(12.dp))
                ) {
                    DefaultTextView(getString(R.string.common_close)) {
                        scope.launch { state.hide() }.invokeOnCompletion {
                            if (!state.isVisible) {
                                onDismiss()
                            }
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun DefaultTextView(str: String, onClick: () -> Unit) = Text(
    modifier = Modifier
        .fillMaxWidth()
        .clickableNoEffect(onClick)
        .padding(vertical = 10.dp),
    text = str,
    style = body01,
    textAlign = TextAlign.Center
)

@Composable
private fun DefaultLayout(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) = Box(
    modifier = Modifier
        .padding(horizontal = 15.dp)
        .offset(y = (-2).dp)
        .shadow(                                // 기존 common custom shadow 사용시 뒤에가 비침
            elevation = 1.dp,
            shape = RoundedCornerShape(12.dp),
            ambientColor = Color.Black.copy(alpha = 0.25f),
            spotColor = Color.Black.copy(alpha = 0.25f)
        )
        .padding(horizontal = 1.dp)
        .offset(y = 2.dp)
        .then(modifier),
    content = content
)