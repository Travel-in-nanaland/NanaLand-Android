package com.jeju.nanaland.ui.report.screen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.UploadImages
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

private const val REASON_LENGTH_MIN = 20
private const val REASON_LENGTH_MAX = 500

@Composable
fun ReportWriteScreen(
    onComplete: (String, List<Uri>) -> Unit
) {
    var reason by remember { mutableStateOf("") }
    var reasonError by remember { mutableStateOf(false) }
    var images by remember { mutableStateOf<List<Uri>>(emptyList()) }

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = getString(R.string.report_write_resone_title),
                    style = title02Bold,
                    color = getColor().gray01
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = getString(R.string.common_필수_with_star),
                    style = caption01,
                    color = getColor().main
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(Modifier.padding(horizontal = 16.dp)) {
                ReasonTextField(
                    text = reason,
                    isError = reasonError,
                    onText = { reason = it }
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = getString(R.string.common_사진) +
                        " / " +
                        getString(R.string.common_동영상),
                style = title02Bold,
                color = getColor().gray01
            )
            Spacer(modifier = Modifier.height(8.dp))
            UploadImages(images) {
                images = it
            }
            Spacer(modifier = Modifier.height(100.dp))
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp)
                .clip(RoundedCornerShape(50))
                .background(getColor().main)
                .padding(11.dp)
                .align(Alignment.BottomCenter)
                .clickableNoEffect {
                    if(reason.length < REASON_LENGTH_MIN)
                        reasonError = true
                    else
                        onComplete(reason, images)
                },
            text = getString(R.string.info_modification_proposal_보내기),
            style = bodyBold,
            color = getColor().white,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
private fun ReasonTextField(
    text: String,
    isError: Boolean,
    onText: (String) -> Unit
) {
    val tl = text.length

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                if (!isError) getColor().gray02 else getColor().warning,
                RoundedCornerShape(12.dp)
            ),
        value = text,
        onValueChange = {
            if(it.length <= REASON_LENGTH_MAX)
                onText(it)
        },
        textStyle = body02.copy(
            color = getColor().black
        ),
        minLines = 4
    ) {
        if(tl == 0)
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = getString(R.string.report_write_resone_hint, REASON_LENGTH_MIN),
                style = body02,
                color = getColor().gray01,
            )

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 6.dp),
            ) {
                it()
            }

            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 10.dp, bottom = 5.dp),
                text = "($tl /$REASON_LENGTH_MAX)",
                style = body02,
                color = getColor().gray01,
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    if(isError)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.ic_warning_outlined),
                contentDescription = null,
                tint = getColor().warning
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = getString(R.string.report_write_resone_error, REASON_LENGTH_MIN),
                style = caption01,
                color = getColor().warning
            )
        }
}