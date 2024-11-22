package com.jeju.nanaland.domain.entity.file

import com.google.gson.annotations.SerializedName

data class FileComplete (
    @SerializedName("uploadId")
    val id: String,
    @SerializedName("fileKey")
    val key: String,
    @SerializedName("parts")
    val parts: List<FilePart>,
)

data class FilePart(
    @SerializedName("partNumber")
    val number: Int,
    @SerializedName("etag")
    val tag: String,
)