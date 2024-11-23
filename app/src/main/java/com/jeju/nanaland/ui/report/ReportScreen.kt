package com.jeju.nanaland.ui.report

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.dialog.SubmitLoadingDialog
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.report.screen.ReportCategoryScreen
import com.jeju.nanaland.ui.report.screen.ReportWriteScreen
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState

@Composable
fun ReportScreen(
    moveToBackScreen: () -> Unit,
    viewModel: ReportViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val page = viewModel.page.collectAsStateWithLifecycle()
    val submitState = viewModel.submitCallState.collectAsStateWithLifecycle()
    val email = viewModel.email.collectAsStateWithLifecycle()
    var cancelDialog by remember { mutableStateOf(false) }
    var isLoadingDialogShowing by remember { mutableStateOf(false) }

    LaunchedEffect(submitState.value) {
        submitState.value?.let {
            when (it) {
                is UiState.Success -> {
                    isLoadingDialogShowing = false
                    Toast.makeText(context, getString(R.string.report_write_complete_toast), Toast.LENGTH_LONG).show()
                    moveToBackScreen()
                }
                is UiState.Loading -> {
                    isLoadingDialogShowing = true
                }
                is UiState.Failure -> {
                    isLoadingDialogShowing = false
                    viewModel.setSubmitCallStateNull()
                    Toast.makeText(context, getString(R.string.common_인터넷_문제), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    BackHandler {
        if(page.value <= 1)
            moveToBackScreen()
        else
            cancelDialog = true
    }

    if(cancelDialog) {
//        DialogCommon(
//            title = getString(R.string.dialog_msg_title_8),
//            subTitle = getString(R.string.dialog_msg_sub_title_8),
//            onDismissRequest = { cancelDialog = false },
//            onPositive = moveToBackScreen,
//            onNegative = { cancelDialog = false }
//        )TODO
    }

    CustomSurface { isImeKeyboardShowing ->
        Scaffold(
            containerColor = getColor().surface,
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            Column(
                modifier = Modifier
                    .imePadding()
                    .padding(bottom = if (isImeKeyboardShowing) 0.dp else it.calculateBottomPadding())
            ) {
                CustomTopBar(
                    title = getString(R.string.common_신고),
                    onBackButtonClicked = {
                        if(page.value <= 1)
                            moveToBackScreen()
                        else
                            cancelDialog = true
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))

                if(page.value == 1)
                    ReportCategoryScreen {
                        viewModel.setReason(it)
                        viewModel.setPage(2)
                    }
                else if(page.value == 2)
                    ReportWriteScreen(email.value) { reason, email, images ->
                        if(submitState.value !is UiState.Loading)
                            viewModel.submit(
                                email = email,
                                claimType = viewModel.reportReason!!,
                                content = reason,
                                images = images.map { UriRequestBody(context, it) },
                            )
                    }
            }
        }
    }

    if (isLoadingDialogShowing) {
        SubmitLoadingDialog(getString(R.string.loading_wait_text_desc3))
    }
}