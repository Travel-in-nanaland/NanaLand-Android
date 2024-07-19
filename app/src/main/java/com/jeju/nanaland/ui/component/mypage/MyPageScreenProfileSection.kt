package com.jeju.nanaland.ui.component.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.jeju.nanaland.ui.component.common.TagChip1
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.caption01SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ColumnScope.MyPageScreenProfileSection(
    profile: UserProfile,
    isMine: Boolean,
    moveToSignInScreen: () -> Unit,
    moveToProfileModificationScreen: () -> Unit,
    moveToTypeTestScreen: () -> Unit,
) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(getColor().main)
        ) {
            if(profile.provider == "GUEST")
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    painter = painterResource(R.drawable.ic_mandarine_filled_white),
                    contentDescription = null
                )
            else
                GlideImage (
                    modifier = Modifier.fillMaxSize(),
                    imageModel = { profile.profileImageUrl }
                )

        }

        Spacer(modifier = Modifier.width(20.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            NicknamePart(
                name = if(profile.provider == "GUEST") null else profile.nickname,
                isMine = isMine,
                moveToSignInScreen = moveToSignInScreen,
                moveToProfileModificationScreen = moveToProfileModificationScreen
            )

            Text(
                text = profile.travelType ?: getString(R.string.mypage_screen_없음),
                color = getColor().main,
                style = body02SemiBold,
            )

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                profile.hashTags?.forEach {
                    TagChip1("#${it}")
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
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(R.drawable.ic_arrow_right),
                        contentDescription = null
                    )
                }
            }
        }
    }

    DescriptionPart(
        text = profile.description ?: ""
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
                if(name == null) moveToSignInScreen()
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

        if(isMine)
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .clickableNoEffect {
                        if(name != null) moveToProfileModificationScreen()
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

@Composable
private fun DescriptionPart(
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
            text = text,
            color = getColor().black,
            style = body02,
        )
    }
}