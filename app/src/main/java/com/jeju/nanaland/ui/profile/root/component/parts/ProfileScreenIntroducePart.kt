package com.jeju.nanaland.ui.profile.root.component.parts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.member.UserProfile
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileScreenIntroducePart(
    profile: UserProfile,
    isMine: Boolean,
    moveToSignInScreen: () -> Unit,
    moveToProfileModificationScreen: () -> Unit,
) {
    val isGuest = profile.provider == "GUEST"
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = if(isGuest) Alignment.CenterVertically else Alignment.Top
    ) {
        Column(
            Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.clickableNoEffect {
                    if(isGuest) moveToSignInScreen()
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if(isGuest) getString(R.string.mypage_screen_로그인이_필요해요) else profile.nickname,
                    color = getColor().black,
                    style = bodyBold,
                )

                if(isMine) {
                    Spacer(Modifier.width(4.dp))

                    if(isGuest) {
                        Icon(
                            modifier = Modifier
                                .size(15.dp)
                                .clickableNoEffect(moveToProfileModificationScreen),
                            painter = painterResource(R.drawable.ic_arrow_right),
                            contentDescription = null,
                            tint = getColor().black,
                        )
                    } else {
                        Icon(
                            modifier = Modifier
                                .size(20.dp)
                                .clickableNoEffect(moveToProfileModificationScreen),
                            painter = painterResource(R.drawable.ic_pencil_outlined),
                            contentDescription = null,
                            tint = getColor().gray01,
                        )
                    }
                }
            }

            Spacer(Modifier.height(4.dp))
            if(!isMine || profile.description.isNotBlank()) {
                Text(
                    text = profile.description.ifBlank { getString(R.string.mypage_screen_desc_default) },
                    color = getColor().black,
                    style = caption01,
                )
            } else if(!isGuest){
                Text(
                    text = getString(R.string.mypage_screen_desc_hint),
                    color = getColor().gray01,
                    style = caption01,
                )
            }
        }

        Spacer(Modifier.width(24.dp))

        GlideImage (
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            imageModel = { profile.profileImage.originUrl }
        )
    }
}