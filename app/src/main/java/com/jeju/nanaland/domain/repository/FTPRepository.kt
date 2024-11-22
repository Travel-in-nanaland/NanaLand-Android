package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.entity.file.FileCategory

interface FTPRepository {

    suspend fun input(
        image: String, // Uri String
        category: FileCategory
    ): String // return fileKey

}