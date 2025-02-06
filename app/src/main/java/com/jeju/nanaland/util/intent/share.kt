package com.jeju.nanaland.util.intent

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.jeju.nanaland.BuildConfig
import com.jeju.nanaland.util.language.getLanguage

@SuppressLint("DefaultLocale")
fun goToShare(context: Context, categoryType: String, contentId: Int?) {
    val shareURL = String.format(
        "%s/share/%s?category=%s&%s",
        BuildConfig.BASE_URL,
        getLanguage().code,
        categoryType,
        contentId?.let { "id=$it" } ?: ""
    )
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareURL)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}