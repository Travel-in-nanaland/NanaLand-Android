//package com.jeju.nanaland.ui.profile.root.component
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.padding
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.jeju.nanaland.domain.entity.notice.NoticeSummery
//import com.jeju.nanaland.ui.profile.component.parts.ProfileNoticeRow
//
//
//@Composable
//fun ProfileScreenNoticeListSection(
//    data: List<NoticeSummery>,
//    onClick: (Int) -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .padding(horizontal = 16.dp, vertical = 24.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        data.forEach {
//            ProfileNoticeRow(it, onClick)
//        }
//    }
//}
