package com.jeju.nanaland.data.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jeju.nanaland.data.api.ReportApi
import com.jeju.nanaland.domain.repository.ReportRepository
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.report.InformationModificationProposalRequest
import com.jeju.nanaland.globalvalue.type.ReportType
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ReportRepositoryImpl(
    private val reportApi: ReportApi
) : ReportRepository, NetworkResultHandler {

    override suspend fun informationModificationProposal(
        data: InformationModificationProposalRequest,
        image: File?
    ): NetworkResult<String?> {
        val multipartImage: MultipartBody.Part? = image?.let {
            val imageBody = image.asRequestBody("image/png".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("multipartFile", imageBody.toString(), imageBody)
        }

        val gson = GsonBuilder().setLenient().setPrettyPrinting().create();
        val json = gson.toJson(data)
        val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())

        return handleResult { reportApi.informationModificationProposal(requestBody, multipartImage) }
    }

    override suspend fun reportReview(
        reviewId: Int,
        claimType: ReportType,
        content: String,
        images: List<UriRequestBody>
    ): NetworkResult<String?> {
        val multipartImage: List<MultipartBody.Part>? = images.takeIf{ it.isNotEmpty() }?.map {
            it.toMultipartBody("multipartFileList")
        }
        val reqData = Gson().toJson(mapOf(
            "reviewId" to reviewId,
            "claimType" to claimType,
            "content" to content,
        )).toRequestBody("application/json".toMediaTypeOrNull())

        return handleResult { reportApi.reportReview(reqData, multipartImage) }
    }
}