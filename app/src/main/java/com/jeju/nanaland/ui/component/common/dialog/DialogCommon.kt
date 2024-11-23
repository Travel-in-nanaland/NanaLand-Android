package com.jeju.nanaland.ui.component.common.dialog

import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.bodySemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import java.util.Calendar

/* test code
var index by remember { mutableStateOf(0) }
DialogCommon(
    type = DialogCommonType.entries[index],
    onDismiss = { index = (index +1) % DialogCommonType.entries.size },
    onYes = {  }
)
*/

enum class DialogCommonType {
    PlzAppReview,    // 앱 후기 작성 촉구 팝업
    AlarmPermission, // 알림 동의 팝업
    Push,            // 서비스 push 알림 동의 팝업
    AlarmDismiss,    // 알림 비동의 팝업
    Language,        // 언어 선택 확인 팝업
    Logout,          // 로그아웃 팝업
    Withdrawal,      // 탈퇴하기 팝업
    Write,           // 프로필 수정 중 뒤로가기 팝업, 리뷰 작성 중 뒤로가기 팝업
    Login,           // 회원가입 유도 팝업
    RemoveReview,    // 리뷰 삭제 팝업
    Internet,        // 인터넷 연결 확인 팝업
}

@Composable
fun DialogCommon(
    type: DialogCommonType,
    onDismiss: () -> Unit,
    onYes: () -> Unit,
    onNo: (() -> Unit) = {},
    onOther: (() -> Unit) = {},
) {
    val currentDate = Calendar.getInstance()

    val (title, subTitle) = remember(type) {
        when (type) {
            DialogCommonType.PlzAppReview -> getString(R.string.dialog_msg_title_1)    to getString(R.string.dialog_msg_sub_title_1)
            DialogCommonType.AlarmPermission -> getString(R.string.dialog_msg_title_2) to getString(R.string.dialog_msg_sub_title_2)
            DialogCommonType.Push -> getString(
                R.string.dialog_msg_title_3, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH) + 1, currentDate.get(Calendar.DATE)
            ) to getString(R.string.dialog_msg_sub_title_3)
            DialogCommonType.AlarmDismiss -> getString(R.string.dialog_msg_title_4)    to getString(R.string.dialog_msg_sub_title_4)
            DialogCommonType.Language -> getString(R.string.dialog_msg_title_5)        to null
            DialogCommonType.Logout -> getString(R.string.dialog_msg_title_6)          to null
            DialogCommonType.Withdrawal -> getString(R.string.dialog_msg_title_7)      to getString(R.string.dialog_msg_sub_title_7)
            DialogCommonType.Write -> getString(R.string.dialog_msg_title_8)           to getString(R.string.dialog_msg_sub_title_8)
            DialogCommonType.Login -> getString(R.string.dialog_msg_title_9)           to null
            DialogCommonType.RemoveReview -> getString(R.string.dialog_msg_title_10)   to null
            DialogCommonType.Internet -> getString(R.string.dialog_msg_title_11)       to null
        }
    }
    val image = remember(type) {
        when (type) {
            DialogCommonType.Login -> R.drawable.ic_logo to Size(72, 72)
            DialogCommonType.Internet -> R.drawable.ic_warning_triangle to Size(38,38)
            else -> null
        }
    }

    Dialog(onDismiss) {
        NanaLandTheme {
            DialogBox(
                image = image,
                title = title,
                subTitle = subTitle,
                innerButtons = {
                    when (type) {
                        DialogCommonType.PlzAppReview -> InnerButton(
                            6.dp,
                            Triple(R.string.dialog_btn_later, false, onNo),
                            Triple(R.string.dialog_btn_write, true, onYes)
                        )
                        DialogCommonType.AlarmPermission -> InnerButton(
                            6.dp,
                            Triple(R.string.dialog_btn_agree, false, onNo),
                            Triple(R.string.dialog_btn_no_agree, true, onYes)
                        )
                        DialogCommonType.Push -> InnerButton(
                            32.dp,
                            Triple(R.string.common_확인, true, onYes),
                        )
                        DialogCommonType.Login -> Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp)
                                .clickableNoEffect(onYes)
                                .clip(RoundedCornerShape(100.dp))
                                .background(getColor().main)
                                .padding(vertical = 9.dp)
                        ) {
                            Row(
                                modifier = Modifier.align(Alignment.Center),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = getString(R.string.dialog_btn_go_sign_up),
                                    style = body02,
                                    color = getColor().white,
                                    textAlign = TextAlign.Center
                                )
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(id = R.drawable.ic_arrow_right),
                                    contentDescription = null,
                                    tint = getColor().white
                                )
                            }
                        }
                        DialogCommonType.Internet -> InnerButton(
                            6.dp,
                            Triple(R.string.dialog_btn_out, false, onNo),
                            Triple(R.string.dialog_btn_retry, true, onYes)
                        )
                        else -> InnerButton(
                            6.dp,
                            Triple(R.string.common_네, false, onYes),
                            Triple(R.string.common_아니오, true, onNo)
                        )
                    }
                },
                outerButtons = {
                    when (type) {
                        DialogCommonType.Login ->  Text(
                            text = getString(R.string.sign_in_screen_로그인_없이_둘러보기),
                            style = body02,
                            color = getColor().gray02,
                            textAlign = TextAlign.Center
                        )
                        else -> {}
                    }
                }
            )
        }
    }
}

@Composable
private fun DialogBox(
    image: Pair<Int, Size>? = null,
    title: String,
    subTitle: String? = null,
    innerButtons: @Composable ColumnScope.() -> Unit,
    outerButtons: (@Composable ColumnScope.() -> Unit) = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(getColor().white)
                .padding(top = 28.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                image?.let {
                    Image(
                        painter = painterResource(id = it.first),
                        modifier = Modifier.size(it.second.width.dp, it.second.height.dp),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }

                Text(
                    text = title,
                    style = bodySemiBold,
                    color = getColor().black,
                    textAlign = TextAlign.Center
                )

                subTitle?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    Column(
                        Modifier
                            .weight(1f, false)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = it,
                            style = body02,
                            color = getColor().gray01,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                innerButtons()
            }
        }
        outerButtons()
    }
}

@Composable
private fun InnerButton(
    horizontalPadding: Dp = 6.dp,
    vararg info: Triple</*text id*/Int, /*isMain*/Boolean, /*onClick*/() -> Unit>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        info.forEach {
            Box(
                modifier = Modifier
                    .clickableNoEffect(it.third)
                    .clip(RoundedCornerShape(100.dp))
                    .background(if (it.second) getColor().main else getColor().gray02)
                    .padding(vertical = 9.dp)
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = getString(it.first),
                    style = body02,
                    color = getColor().white,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}
