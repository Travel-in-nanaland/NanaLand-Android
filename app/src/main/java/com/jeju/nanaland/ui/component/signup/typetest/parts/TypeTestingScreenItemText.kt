package com.jeju.nanaland.ui.component.signup.typetest.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun TypeTestingScreenItemText(itemIdx: Int) {
    Text(
        text = when (itemIdx) {
            1 -> getString(R.string.type_test_screen_item1)
            2 -> getString(R.string.type_test_screen_item2)
            3 -> getString(R.string.type_test_screen_item3)
            4 -> getString(R.string.type_test_screen_item4)
            5 -> getString(R.string.type_test_screen_item5)
            6 -> getString(R.string.type_test_screen_item6)
            7 -> getString(R.string.type_test_screen_item7)
            8 -> getString(R.string.type_test_screen_item8)
            9 -> getString(R.string.type_test_screen_item9)
            10 -> getString(R.string.type_test_screen_item10)
            11 -> getString(R.string.type_test_screen_item11)
            else -> getString(R.string.type_test_screen_item12)
        },
        color = getColor().black,
        style = body02SemiBold
    )
}