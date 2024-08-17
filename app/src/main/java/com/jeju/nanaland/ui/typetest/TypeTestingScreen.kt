package com.jeju.nanaland.ui.typetest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.globalvalue.constant.TravelType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.signup.typetest.TypeTestingScreenBackButton
import com.jeju.nanaland.ui.component.signup.typetest.TypeTestingScreenBottomButton
import com.jeju.nanaland.ui.component.signup.typetest.TypeTestingScreenLevelText
import com.jeju.nanaland.ui.component.signup.typetest.TypeTestingScreenProgressBar
import com.jeju.nanaland.ui.component.signup.typetest.TypeTestingScreenQuestionText
import com.jeju.nanaland.ui.component.signup.typetest.TypeTestingScreenSelectableItem
import com.jeju.nanaland.ui.component.signup.typetest.TypeTestingScreenSkipTestText

@Composable
fun TypeTestingScreen(
    moveToTypeTestCompletionScreen: (TravelType) -> Unit,
    moveToBackScreen: () -> Unit,
    viewModel: TypeTestingViewModel = hiltViewModel()
) {
    val level = viewModel.level.collectAsState().value
    val selectionList = viewModel.selectionList
    TypeTestingScreen(
        level = level,
        updateLevel = viewModel::updateLevel,
        selectionList = selectionList,
        updateUserType = viewModel::updateUserType,
        moveToTypeTestCompletionScreen = moveToTypeTestCompletionScreen,
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun TypeTestingScreen(
    level: Int,
    updateLevel: (Int) -> Unit,
    selectionList: SnapshotStateList<Int>,
    updateUserType: ((TravelType) -> Unit) -> Unit,
    moveToTypeTestCompletionScreen: (TravelType) -> Unit,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(32.dp))

            TypeTestingScreenLevelText(level = level)

            Spacer(Modifier.height(8.dp))

            TypeTestingScreenProgressBar(level = level)

            Spacer(Modifier.height(80.dp))

            TypeTestingScreenQuestionText(level = level)

            when (level) {
                5 -> {
                    Spacer(Modifier.height(40.dp))

                    Column {
                        Row(
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                        ) {
                            TypeTestingScreenSelectableItem(
                                isSelected = selectionList[level - 1] == 1,
                                itemIdx = level * 2 - 1,
                                onClick = { selectionList[level - 1] = 1 }
                            )

                            Spacer(Modifier.width(40.dp))

                            TypeTestingScreenSelectableItem(
                                isSelected = selectionList[level - 1] == 2,
                                itemIdx = level * 2,
                                onClick = { selectionList[level - 1] = 2 }
                            )
                        }

                        Spacer(Modifier.height(24.dp))

                        Row(
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                        ) {
                            TypeTestingScreenSelectableItem(
                                isSelected = selectionList[level - 1] == 3,
                                itemIdx = level * 2 + 1,
                                onClick = { selectionList[level - 1] = 3 }
                            )

                            Spacer(Modifier.width(40.dp))

                            TypeTestingScreenSelectableItem(
                                isSelected = selectionList[level - 1] == 4,
                                itemIdx = level * 2 + 2,
                                onClick = { selectionList[level - 1] = 4 }
                            )
                        }
                    }
                }
                else -> {
                    Spacer(Modifier.height(100.dp))

                    Row(
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                    ) {
                        TypeTestingScreenSelectableItem(
                            isSelected = selectionList[level - 1] == 1,
                            itemIdx = level * 2 - 1,
                            onClick = { selectionList[level - 1] = 1 }
                        )

                        Spacer(Modifier.width(40.dp))

                        TypeTestingScreenSelectableItem(
                            isSelected = selectionList[level - 1] == 2,
                            itemIdx = level * 2,
                            onClick = { selectionList[level - 1] = 2 }
                        )
                    }
                }
            }
        }

        Spacer(Modifier.weight(1f))

        when (level) {
            1 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TypeTestingScreenSkipTestText {
                        moveToBackScreen()
                    }

                    Spacer(Modifier.height(16.dp))

                    TypeTestingScreenBottomButton(
                        isActivated = selectionList[0] != 0,
                        onClick = {
                            updateLevel(level + 1)
                        }
                    )
                }
            }
            else -> {
                Row {
                    TypeTestingScreenBackButton {
                        updateLevel(level - 1)
                    }

                    Spacer(Modifier.weight(1f))

                    TypeTestingScreenBottomButton(
                        isActivated = selectionList[level - 1] != 0,
                        onClick = {
                            if (level == 5) {
                                updateUserType(moveToTypeTestCompletionScreen)
                            } else {
                                updateLevel(level + 1)
                            }
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))
    }
}