package com.jeju.nanaland.domain.entity.file

import com.google.gson.annotations.SerializedName

data class FileInitResponse (
    @SerializedName("uploadId")
    val id: String,
    @SerializedName("fileKey")
    val key: String,
    @SerializedName("presignedUrlInfos")
    val infos: List<PreSignedInfo>,
)

data class PreSignedInfo (
    @SerializedName("partNumber")
    val number: Int,
    @SerializedName("preSignedUrl")
    val url: String
)