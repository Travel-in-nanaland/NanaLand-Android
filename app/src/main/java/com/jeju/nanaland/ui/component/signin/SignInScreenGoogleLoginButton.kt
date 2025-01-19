package com.jeju.nanaland.ui.component.signin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.appleSdGothicNeo
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun SignInScreenGoogleLoginButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = getColor().gray01
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .clickableNoEffect { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(R.drawable.ic_google),
            contentDescription = null
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = getString(R.string.sign_in_screen_google_sign_in),
            color = Color(0xFF000000),
            fontFamily = appleSdGothicNeo,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}