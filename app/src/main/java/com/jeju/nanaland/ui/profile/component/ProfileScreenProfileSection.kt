package com.jeju.nanaland.ui.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.member.UserProfile
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.caption01SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreenProfileSection(
    profile: UserProfile,
    isMine: Boolean,
    moveToSignInScreen: () -> Unit,
    moveToProfileModificationScreen: () -> Unit,
    moveToTypeTestScreen: () -> Unit,
    moveToTypeTestResultScreen: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        GlideImage (
            modifier = Modifier
                .padding(top = 4.dp)
                .size(80.dp)
                .clip(CircleShape),
            imageModel = { profile.profileImage.originUrl }
        )

        Spacer(modifier = Modifier.width(23.dp))

        Column(
            modifier = Modifier.heightIn(80.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 4.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            NicknamePart(
                name = if(profile.provider == "GUEST") null else profile.nickname,
                isMine = isMine,
                moveToSignInScreen = moveToSignInScreen,
                moveToProfileModificationScreen = moveToProfileModificationScreen
            )
            if(profile.provider == "GUEST")
                return@Column

            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape (30.dp))
                    .border(
                        1.dp,
                        getColor().main,
                        RoundedCornerShape (30.dp)
                    )
                    .background(getColor().white)
                    .padding(horizontal = 12.dp, vertical = 2.dp)
                    .clickableNoEffect { moveToTypeTestResultScreen() },
                text = profile.travelType ?: getString(R.string.mypage_screen_없음),
                color = getColor().main,
                style = caption01,
            )

            FlowRow(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                profile.hashTags.forEach {
                    Text(
                        text = "#$it",
                        color = getColor().main,
                        style = caption01,
                    )
                }
            }

            if(isMine){
                Row(
                    modifier = Modifier.clickableNoEffect { moveToTypeTestScreen() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = getString(
                            if(profile.travelType == null)
                                R.string.mypage_screen_테스트_하기
                            else
                                R.string.mypage_screen_테스트_다시하기
                        ),
                        color = getColor().black,
                        style = caption01SemiBold
                    )

                    Spacer(Modifier.width(4.dp))

                    Image(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(R.drawable.ic_arrow_right),
                        contentDescription = null
                    )
                }
            }
        }
    }
    if(profile.provider != "GUEST")
        DescriptionPart(
            isMine = isMine,
            text = profile.description
        )
}

@Composable
private fun NicknamePart(
    name: String?,
    isMine: Boolean,
    moveToSignInScreen: () -> Unit,
    moveToProfileModificationScreen: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickableNoEffect {
                if (name == null) moveToSignInScreen()
            }
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f, false),
            text = name ?: getString(R.string.mypage_screen_로그인이_필요해요),
            color = getColor().black,
            style = title02Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        if(isMine) {
            Spacer(Modifier.width(4.dp))

            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .clickableNoEffect {
                        if (name != null) moveToProfileModificationScreen()
                    },
                painter = painterResource(
                    if(name == null) R.drawable.ic_arrow_right
                    else R.drawable.ic_pencil_outlined
                ),
                contentDescription = null,
                tint = getColor().gray01,
            )
        }
    }
}

@Composable
private fun DescriptionPart(
    isMine: Boolean,
    text: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(5.dp, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(getColor().white)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = text.ifBlank {
                if(isMine) getString(R.string.mypage_screen_desc_hint)
                else getString(R.string.mypage_screen_desc_default)
            },
            color = if(isMine && text.isBlank())
                getColor().gray02
            else
                getColor().black,
            style = body02,
            minLines = 3
        )
    }
}