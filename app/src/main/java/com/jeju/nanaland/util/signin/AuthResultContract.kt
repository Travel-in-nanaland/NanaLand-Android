package com.jeju.nanaland.util.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task

class AuthResultContract : ActivityResultContract<Int, Task<GoogleSignInAccount>?>() {
    override fun createIntent(context: Context, input: Int): Intent =
        getGoogleSignInClient(context).signInIntent.putExtra("input", input)

    override fun parseResult(resultCode: Int, intent: Intent?): Task<GoogleSignInAccount>? {
        return when (resultCode) {
            Activity.RESULT_OK -> GoogleSignIn.getSignedInAccountFromIntent(intent)
            else -> null
        }
    }
}

fun getGoogleSignInClient(context: Context): GoogleSignInClient {
    val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        // Android Cliend Id
//        .requestIdToken("345399915859-trsequqmihm9t6lsko0oldaffejkdl4r.apps.googleusercontent.com")
        // Web Client Id -> 이걸로 해야 구글 로그인이 된다.
//        .requestIdToken("345399915859-8hpfptcsbvhd0h2pu476p6dl17r1hgqj.apps.googleusercontent.com")
        .requestEmail()
        .build()

    return GoogleSignIn.getClient(context, signInOptions)
}