package com.jeju.nanaland.ui.component.signup.typetest.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R

@Composable
fun TypeTestingScreenItemImage(itemIdx: Int) {
    Image(
        modifier = Modifier
            .size(
                when (itemIdx) {
                    1 -> 80
                    2 -> 80
                    3 -> 70
                    4 -> 100
                    5 -> 80
                    6 -> 80
                    7 -> 80
                    8 -> 95
                    9 -> 80
                    10 -> 80
                    11 -> 80
                    else -> 80
                }.dp
            )
            .offset(x = 0.dp, y = if (itemIdx == 10) (-7).dp else 0.dp),
        painter = painterResource(id = when (itemIdx) {
            1 -> R.drawable.img_type_test_1
            2 -> R.drawable.img_type_test_2
            3 -> R.drawable.img_type_test_3
            4 -> R.drawable.img_type_test_4
            5 -> R.drawable.img_type_test_5
            6 -> R.drawable.img_type_test_6
            7 -> R.drawable.img_type_test_7
            8 -> R.drawable.img_type_test_8
            9 -> R.drawable.img_type_test_9
            10 -> R.drawable.img_type_test_10
            11 -> R.drawable.img_type_test_11
            else -> R.drawable.img_type_test_12
        }),
        contentDescription = null
    )
}