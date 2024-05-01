package com.nanaland.ui.festival

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.nanaland.globalvalue.type.FestivalCategoryType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class FestivalListViewModel @Inject constructor() : ViewModel() {

    private val _selectedCategoryType = MutableStateFlow(FestivalCategoryType.Monthly)
    val selectedCategoryType = _selectedCategoryType.asStateFlow()
    val selectedLocationList = mutableStateListOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false)
    private val _startCalendar = MutableStateFlow<Calendar?>(null)
    val startCalendar = _startCalendar.asStateFlow()
    private val _endCalendar = MutableStateFlow<Calendar?>(null)
    val endCalendar = _endCalendar.asStateFlow()

    fun updateSelectedCategoryType(type: FestivalCategoryType) {
        _selectedCategoryType.update { type }
    }

    fun updateStartCalendar(calendar: Calendar?) {
        _startCalendar.update { calendar }
    }

    fun updateEndCalendar(calendar: Calendar?) {
        _endCalendar.update { calendar }
    }
}