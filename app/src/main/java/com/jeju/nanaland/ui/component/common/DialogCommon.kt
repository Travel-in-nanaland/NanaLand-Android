package com.jeju.nanaland.ui.component.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun DialogCommon(
    onDismissRequest: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    subTitle: String? = null,
    onPositive: (() -> Unit)? = null,
    onNegative: (() -> Unit)? = null,
) {

    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        NanaLandTheme {
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(getColor().white)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        text = title,
                        style = title01Bold,
                        color = getColor().black,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    subTitle?.let {
                        Text(
                            text = it,
                            style = body01,
                            color = getColor().gray01,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    HorizontalDivider(color = getColor().gray02)

                    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                        onPositive?.let {
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 12.dp)
                                    .clickableNoEffect { onPositive() },
                                text = getString(R.string.common_네),
                                style = title02Bold,
                                color = getColor().black,
                                textAlign = TextAlign.Center
                            )
                        }

                        if(onPositive != null && onNegative != null)
                            VerticalDivider(color = getColor().gray02)

                        onNegative?.let {
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 12.dp)
                                    .clickableNoEffect { onNegative() },
                                text = getString(R.string.common_아니오),
                                style = title02Bold,
                                color = getColor().main,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}