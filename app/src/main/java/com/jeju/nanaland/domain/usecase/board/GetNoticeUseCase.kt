package com.jeju.nanaland.domain.usecase.board

import com.jeju.nanaland.domain.repository.BoardRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNoticeUseCase @Inject constructor(
    private val repository: BoardRepository
) {
    operator fun invoke(
        id: Int
    ) = flow {
        val response = repository.getNotice(id)
        emit(response)
    }
}