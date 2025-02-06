package com.jeju.nanaland.ui.report.screen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.emailRegex
import com.jeju.nanaland.ui.component.common.UploadImages
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect

private const val REASON_LENGTH_MIN = 20
private const val REASON_LENGTH_MAX = 500

@Composable
fun ReportWriteScreen(
    myEmail: String,
    onComplete: (String, String, List<Uri>) -> Unit
) {
    var reason by remember { mutableStateOf("") }
    var reasonError by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf(myEmail) }
    var emailError by remember { mutableStateOf(false) }
    var images by remember { mutableStateOf<List<Uri>>(emptyList()) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = getString(R.string.report_write_resone_title),
                    style = bodyBold,
                    color = getColor().black
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = getString(R.string.common_필수_with_star),
                    style = caption01,
                    color = getColor().main
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            InputTextField(
                text = reason,
                hint = getString(R.string.report_write_resone_hint, REASON_LENGTH_MIN),
                minLines = 4,
                error = if(reasonError) getString(R.string.report_write_resone_error, REASON_LENGTH_MIN) else null,
                maxLength = REASON_LENGTH_MAX,
                onText = {
                    reason = it
                    reasonError = reason.length < REASON_LENGTH_MIN
                }
            )

            Spacer(modifier = Modifier.height(48.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = getString(R.string.common_이메일),
                    style = bodyBold,
                    color = getColor().black
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = getString(R.string.common_필수_with_star),
                    style = caption01,
                    color = getColor().main
                )
            }
            Text(
                text = getString(R.string.report_write_email_subtitle),
                style = caption01,
                color = getColor().gray01
            )
            Spacer(modifier = Modifier.height(12.dp))
            InputTextField(
                text = email,
                hint = getString(R.string.info_modification_proposal_hint2, REASON_LENGTH_MIN),
                error = if(emailError) getString(R.string.info_modification_proposal_warning) else null,
                height = 48,
                onText = {
                    email = it
                    emailError = !email.matches(emailRegex)
                }
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = getString(R.string.common_사진_동영상),
                style = bodyBold,
                color = getColor().black
            )
            Spacer(modifier = Modifier.height(8.dp))
            UploadImages(images.map { it.toString() }) {
                images = it.map { Uri.parse(it) }
            }
            Spacer(modifier = Modifier.height(48.dp))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp)
                    .clip(RoundedCornerShape(50))
                    .background(if(!reasonError && !emailError)getColor().main else getColor().main10)
                    .padding(11.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickableNoEffect {
//                    reasonError = reason.length < REASON_LENGTH_MIN
//                    emailError = !email.matches(emailRegex)
                        if(!reasonError && !emailError)
                            onComplete(reason, email, images)
                    },
                text = getString(R.string.info_modification_proposal_보내기),
                style = bodyBold,
                color = getColor().white,
                textAlign = TextAlign.Center
            )
        }

}


@Composable
private fun InputTextField(
    text: String,
    hint: String,
    minLines: Int = 1,
    error: String? = null,
    maxLength: Int? = null,
    height: Int? = null,
    onText: (String) -> Unit
) {
    val tl = text.length

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (height != null) Modifier.height(height.dp) else Modifier
            )
            .border(
                1.dp,
                if (error == null) getColor().gray02 else getColor().warning,
                RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        value = text,
        onValueChange = {
            if(it.length <= REASON_LENGTH_MAX)
                onText(it)
        },
        textStyle = body02.copy(
            color = getColor().black
        ),
        minLines = minLines
    ) {
        if(tl == 0)
            Text(
                text = hint,
                style = body02,
                color = getColor().gray01,
            )

        Column(
            verticalArrangement = if (height == null) Arrangement.Top else Arrangement.Center
        ) {
            it()
            maxLength?.let {
                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 10.dp, bottom = 5.dp),
                    text = "($tl /$it)",
                    style = body02,
                    color = getColor().gray01,
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.alpha(if(error == null) 0f else 1f),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.ic_warning_outlined),
                contentDescription = null,
                tint = getColor().warning
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = error ?: " ",
                style = caption01,
                color = getColor().warning
            )
        }
}