package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R

@Composable
fun TypeTestResultScreenImage() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        painter = painterResource(R.drawable.img_type_test_1),
        contentDescription = null
    )
}