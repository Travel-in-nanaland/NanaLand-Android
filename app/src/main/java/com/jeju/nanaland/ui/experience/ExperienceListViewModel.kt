package com.jeju.nanaland.ui.experience

import androidx.lifecycle.ViewModel
import com.jeju.nanaland.globalvalue.type.ExperienceCategoryType
import com.jeju.nanaland.globalvalue.type.FestivalCategoryType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ExperienceListViewModel @Inject constructor() : ViewModel() {

    private val _selectedCategoryType = MutableStateFlow(ExperienceCategoryType.Activity)
    val selectedCategoryType = _selectedCategoryType.asStateFlow()

    fun updateSelectedCategoryType(type: ExperienceCategoryType) {

    }
}