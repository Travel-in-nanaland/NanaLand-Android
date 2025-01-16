package com.jeju.nanaland.ui.component.review.parts

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.LanguageType
import com.jeju.nanaland.ui.component.common.ExpandableText
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.resource.getString

@Composable
fun ReviewContent(
    text: String,
    onExpanded: (Boolean) -> Unit,
) {
    ExpandableText(
        text = text,
        style = body02.copy(color = getColor().black),
        collapsedMaxLine = when (getLanguage()) {
            LanguageType.Korean, LanguageType.Chinese, LanguageType.Vietnam -> 2
            LanguageType.English, LanguageType.Malaysia -> 3
        },
        showMoreText = getString(R.string.detail_screen_common_더보기),
        showMoreStyle = caption01.copy(color = getColor().gray01),
        showLessText = getString(R.string.detail_screen_common_접기),
        onExpanded = onExpanded
    )
}