package com.jeju.nanaland.ui.component.signup.profilesetting

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.SignUpNicknameState
import com.jeju.nanaland.ui.component.signup.profilesetting.parts.SignUpScreenTextFieldHint
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun SignUpScreenTextField(
    inputText: String,
    onValueChange: (String) -> Unit,
    inputState: SignUpNicknameState
) {
    BasicTextField(
        value = inputText,
        onValueChange = { onValueChange(it) },
        maxLines = 1
    ) {
        Column(
            modifier = Modifier.animateContentSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (inputState == SignUpNicknameState.Idle) getColor().gray02 else getColor().warning
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                it()
                if (inputText == "") {
                    SignUpScreenTextFieldHint()
                }
            }

            if (inputState != SignUpNicknameState.Idle) {
                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(R.drawable.ic_warning_outlined),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(getColor().warning)
                    )

                    Spacer(Modifier.width(4.dp))

                    Text(
                        text = if (inputState == SignUpNicknameState.Invalid) "해당 닉네임은 사용할 수 없습니다."
                                else "해당 닉네임은 다른 사용자가 사용 중입니다.",
                        color = getColor().warning,
                        style = caption01
                    )
                }
            }
        }
    }
}