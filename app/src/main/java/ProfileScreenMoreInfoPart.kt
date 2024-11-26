//package com.jeju.nanaland.ui.profile.root.component.parts
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import com.jeju.nanaland.R
//import com.jeju.nanaland.ui.theme.caption01SemiBold
//import com.jeju.nanaland.ui.theme.getColor
//import com.jeju.nanaland.util.resource.getString
//import com.jeju.nanaland.util.ui.clickableNoEffect
//
//@Composable
//fun ProfileScreenMoreInfoPart(
//    isReviewList: Boolean,
//    listSize: Int,
//    moveToListPage: () -> Unit,
//    moveToReviewWriteScreen: () -> Unit,
//) {
//    Row(
//        modifier = Modifier
//            .padding(horizontal = 16.dp)
//            .padding(top = 16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        ProfileShowAll(
//            cnt = listSize,
//            onClick = moveToListPage
//        )
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        Row(
//            modifier = Modifier
//                .alpha(if (isReviewList) 1f else 0f)
//                .border(
//                    border = BorderStroke(
//                        width = 1.dp,
//                        color = getColor().main,
//                    ),
//                    shape = RoundedCornerShape(50)
//                )
//                .padding(horizontal = 10.dp, vertical = 5.dp)
//                .clickableNoEffect {
//                    if (isReviewList) moveToReviewWriteScreen()
//                },
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                modifier = Modifier.size(20.dp),
//                painter = painterResource(id = R.drawable.ic_pencil_outlined),
//                contentDescription = null,
//                tint = getColor().main
//            )
//            Text(
//                text = getString(R.string.mypage_screen_리뷰작성하기),
//                color = getColor().black,
//                style = caption01SemiBold,
//            )
//        }
//    }
//}
