package com.jeju.nanaland.ui.component.infomodification.writing

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun InfoModificationProposalWritingScreenTextField(
    height: Int,
    hint: String,
    inputText: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = getColor().gray02
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        value = inputText,
        onValueChange = onValueChange,
        textStyle = body02
    ) {
        if (inputText == "") {
            Text(
                text = hint,
                color = getColor().gray02,
                style = body02
            )
        }
        it()
    }
}