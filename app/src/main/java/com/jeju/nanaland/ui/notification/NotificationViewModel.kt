package com.jeju.nanaland.ui.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jeju.nanaland.domain.usecase.board.GetNotificationListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    getNotificationListUseCase: GetNotificationListUseCase
): ViewModel() {
    var data = getNotificationListUseCase()
        .flow
        .cachedIn(viewModelScope)
}