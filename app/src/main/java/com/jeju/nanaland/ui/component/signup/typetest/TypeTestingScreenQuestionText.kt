package com.jeju.nanaland.ui.component.signup.typetest

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.util.language.customContext
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.resource.getString

@Composable
fun TypeTestingScreenQuestionText(level: Int) {
    Text(
        text = when (level) {
            1 -> getString(R.string.type_test_screen_question1) + UserData.nickname + getString(R.string.type_test_screen_question_님은1)
            2 -> getString(R.string.type_test_screen_question2) + UserData.nickname + getString(R.string.type_test_screen_question_님은2)
            3 -> getString(R.string.type_test_screen_question3) + UserData.nickname + getString(R.string.type_test_screen_question_님은3)
            4 -> getString(R.string.type_test_screen_question4) + UserData.nickname + getString(R.string.type_test_screen_question_님은4)
            else -> when (getLanguage()) {
                "ko" -> getString(R.string.type_test_screen_question5)
                "ms" -> getString(R.string.type_test_screen_question5) + UserData.nickname + getString(R.string.type_test_screen_question6)
                "zh" -> ""
                else -> ""
            }
        },
        color = getColor().main,
        style = largeTitle02,
        textAlign = TextAlign.Center,
    )
}