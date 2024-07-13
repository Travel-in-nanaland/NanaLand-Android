package com.jeju.nanaland.ui.reviewwrite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.ui.component.common.BottomOkButtonOutlined
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.ui.theme.title02
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview

@ScreenPreview
@Composable
private fun ReviewWriteCompleteScreenPreview() {
    ReviewWriteCompleteUI(1, {}, {})
}

@Composable
fun ReviewWriteCompleteScreen(
    navController: NavController,
) {
    val version = remember { mutableIntStateOf( (1..2).random()) }
    ReviewWriteCompleteUI(
        version = version.intValue,
        onAgain = { navController.popBackStack() },
        onAdd = {}
    )
}


@Composable
private fun ReviewWriteCompleteUI(
    version: Int = 1,
    onAgain: () -> Unit,
    onAdd: () -> Unit,
) {

    CustomSurface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(80.dp))

            Image(
                modifier = Modifier.width(250.dp),
                painter = painterResource(
                    when(version){
                        1 -> R.drawable.img_star
                        else -> R.drawable.img_salad
                    }
                ),
                contentDescription = null,
            )

            Spacer(Modifier.height(40.dp))

            Text(
                text = getString(
                    when(version){
                        1 -> R.string.review_write_keyword_complete_title1
                        else -> R.string.review_write_keyword_complete_title2
                    }
                ),
                color = getColor().main,
                style = largeTitle02,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = getString(
                    when(version){
                        1 -> R.string.review_write_keyword_complete_sub1
                        else -> R.string.review_write_keyword_complete_sub2
                    }
                ),
                color = getColor().black,
                style = title02,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.weight(1f))

            BottomOkButton(
                text = getString(R.string.review_write_keyword_complete_btn_again),
                isActivated = true,
                onClick = onAdd
            )

            Spacer(Modifier.height(16.dp))

            BottomOkButtonOutlined(
                text = getString(R.string.review_write_keyword_complete_btn_add),
                onClick = onAgain
            )

            Spacer(Modifier.height(20.dp))
        }
    }
}