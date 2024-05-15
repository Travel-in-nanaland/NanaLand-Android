package com.jeju.nanaland.ui.component.signin

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jeju.nanaland.ui.theme.appleSdGothicNeo
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun SignInScreenLogoText1() {
    Text(
        text = "nanaland",
        color = getColor().main,
        fontFamily = appleSdGothicNeo,
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 2.sp
    )
}