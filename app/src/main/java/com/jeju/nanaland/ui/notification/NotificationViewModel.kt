package com.jeju.nanaland.ui.notification

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject

enum class TEMP_NotificationType{
    ISSUE, NANA_PICK, LIKE
}
data class TEMP_Notification(
    val id: Int,
    val type: TEMP_NotificationType,
    val title: String,
    val subTitle: String,
    val date: Date
)

@HiltViewModel
class NotificationViewModel @Inject constructor(
): ViewModel() {

    var data: List<TEMP_Notification> = TEMPCall()

    private fun TEMPCall(): List<TEMP_Notification>{
        val testDataSet = listOf(
            Triple(TEMP_NotificationType.NANA_PICK,"[나나’sPick] TOP 10 K-Culture", "제주도의 한옥스테이 궁금하지 않나요?\uD83D\uDC9B"),
            Triple(TEMP_NotificationType.ISSUE,"[이번주 이슈] 서비스 개편 사항", "2월 1째주 가장 좋아요를 많이 받은 곳들을 소개해줄게요\uD83D\uDC9B"),
            Triple(TEMP_NotificationType.LIKE,"[나의 찜] 도두봉", "가장 좋아요를 많이 받은 곳들을 소개해줄게요\uD83D\uDC9B")
        )

        return List(100) {
            val data = testDataSet.random()
            TEMP_Notification(
                it,
                data.first,
                data.second + " ($it)",
                data.third,
                Date(Date().time - (1000 * 60 * 60 * it))
            )
        }
    }
}