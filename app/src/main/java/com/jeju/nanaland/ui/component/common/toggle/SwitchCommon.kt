package com.jeju.nanaland.ui.component.common.toggle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun SwitchCommon(
    checked: Boolean,
    width: Int = 36,
    height: Int = 20,
    modifier: Modifier = Modifier,
    onCheckedChange: ((Boolean) -> Unit)?,
) {
    Switch(
        modifier = Modifier
            .scale((width/52f + height/32f) / 2)  // https://m3.material.io/components/switch/specs#34f9a158-a2b3-4c15-841b-578978126788
            .size(width.dp, height.dp)
            .then(modifier),
        checked = checked,
        onCheckedChange = onCheckedChange,
        thumbContent = {
            Spacer(
                Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(getColor().white)
            )
        },
        colors = SwitchDefaults.colors(
            checkedTrackColor = getColor().main,
            uncheckedTrackColor = getColor().gray02,
            uncheckedBorderColor = Color.Transparent
        ),
    )
}