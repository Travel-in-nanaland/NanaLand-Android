package com.jeju.nanaland.ui.component.signin

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jeju.nanaland.ui.theme.appleSdGothicNeo
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun SignInScreenLogoText2() {
    Text(
        text = "in Jeju",
        color = getColor().main,
        fontFamily = appleSdGothicNeo,
        fontSize = 25.sp,
        fontWeight = FontWeight.Light,
        letterSpacing = 1.sp
    )
}