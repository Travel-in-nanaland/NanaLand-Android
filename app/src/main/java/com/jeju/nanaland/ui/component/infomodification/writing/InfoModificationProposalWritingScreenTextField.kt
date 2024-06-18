package com.jeju.nanaland.ui.component.infomodification.writing

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
import com.jeju.nanaland.globalvalue.type.InputNicknameState
import com.jeju.nanaland.ui.component.signup.profilesetting.parts.SignUpScreenTextFieldHint
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun InfoModificationProposalWritingScreenTextField(
    height: Int,
    hint: String,
    inputText: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean = true,
) {
    BasicTextField(
        value = inputText,
        onValueChange = onValueChange,
        textStyle = body02
    ) {
        Column(
            modifier = Modifier.animateContentSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height.dp)
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (isValid) getColor().gray02 else getColor().warning
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                it()
                if (inputText == "") {
                    Text(
                        text = hint,
                        color = getColor().gray01,
                        style = body02
                    )
                }
            }

            if (!isValid) {
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
                        text = getString(R.string.info_modification_proposal_warning),
                        color = getColor().warning,
                        style = caption01
                    )
                }
            }
        }
    }
}