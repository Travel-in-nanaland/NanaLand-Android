package com.jeju.nanaland.data.repository

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.jeju.nanaland.data.api.FTPApi
import com.jeju.nanaland.domain.entity.file.FileCategory
import com.jeju.nanaland.domain.entity.file.FileComplete
import com.jeju.nanaland.domain.entity.file.FileInitCommand
import com.jeju.nanaland.domain.entity.file.FilePart
import com.jeju.nanaland.domain.entity.file.PreSignedInfo
import com.jeju.nanaland.domain.repository.FTPRepository
import com.jeju.nanaland.util.network.NetworkResultHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.RequestBody.Companion.toRequestBody

class FTPRepositoryImpl(
    private val api: FTPApi,
    @ApplicationContext private val context: Context
): FTPRepository, NetworkResultHandler {
    private val chunkSize = 5 * 1024 * 1024 // 5MB

    override suspend fun input(
        image: String,
        category: FileCategory
    ): String {
        val (fileSize, fileName) = getFileInfo(Uri.parse(image))
        val chunkCount = getFileChunkCount(fileSize)
        val result = api.init(
            FileInitCommand(
                name = fileName,
                size = fileSize,
                partCount = chunkCount,
                category = category
            )
        )

//        CoroutineScope(Dispatchers.IO).launch { // todo? background
            val resultPartInfos = putFileByChunk(Uri.parse(image), result.data.infos)

            api.complete(
                FileComplete(
                    result.data.id,
                    result.data.key,
                    resultPartInfos
                )
            )
//        }

        return result.data.key
    }

    private fun getFileChunkCount(fileSize: Long): Int {
        return (if (fileSize % chunkSize == 0L) fileSize / chunkSize else fileSize / chunkSize + 1).toInt()
    }

    private suspend fun putFileByChunk(uri: Uri, infoList: List<PreSignedInfo>):List<FilePart> = coroutineScope {
        val chunkList = uri.toChunks()
        val resultPartInfos = mutableListOf<FilePart>()
        val uploadJobs = infoList.map { info ->
            launch {
                val response = api.put(info.url, chunkList[info.number - 1].toRequestBody())
                response.headers()["ETag"]?.let { eTag ->
                    resultPartInfos.add(FilePart(info.number, eTag))
                }
            }
        }

        uploadJobs.forEach { it.join() }

        return@coroutineScope resultPartInfos
    }

    private fun getFileInfo(uri: Uri): Pair<Long, String>{
        context.contentResolver.query(
            uri,
            arrayOf(MediaStore.MediaColumns.SIZE, MediaStore.MediaColumns.DISPLAY_NAME),
            null,
            null,
            null
        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                return cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE)) to
                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME))
            }
        }
        throw Exception("File not found")
    }

    private fun Uri.toChunks(): List<ByteArray> {
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(this) ?: throw Exception("Unable to open input stream")
        val chunks = mutableListOf<ByteArray>()
        val buffer = ByteArray(chunkSize)

        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            val chunk = buffer.copyOfRange(0, bytesRead)
            chunks.add(chunk)
        }

        inputStream.close()
        return chunks
    }

}