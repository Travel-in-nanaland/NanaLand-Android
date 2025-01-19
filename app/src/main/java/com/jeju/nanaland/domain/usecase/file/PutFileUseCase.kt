package com.jeju.nanaland.domain.usecase.file

import com.jeju.nanaland.domain.entity.file.FileCategory
import com.jeju.nanaland.domain.repository.FTPRepository
import javax.inject.Inject

class PutFileUseCase @Inject constructor(
    private val repository: FTPRepository
) {
    suspend operator fun invoke(
        image: String, // Uri String
        category: FileCategory
    ) = repository.input(image, category)
}