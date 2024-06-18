package com.jeju.nanaland.util.daterangecalendar.customviews

import android.annotation.TargetApi
import android.content.Context
import android.os.Build.VERSION_CODES
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.jeju.nanaland.R
import com.jeju.nanaland.util.daterangecalendar.customviews.CalendarDateRangeManager.DateSelectionState.IN_SELECTED_RANGE
import com.jeju.nanaland.util.daterangecalendar.customviews.CalendarDateRangeManager.DateSelectionState.LAST_DATE
import com.jeju.nanaland.util.daterangecalendar.customviews.CalendarDateRangeManager.DateSelectionState.START_DATE
import com.jeju.nanaland.util.daterangecalendar.customviews.CalendarDateRangeManager.DateSelectionState.START_END_SAME
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.Companion.getContainerKey
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState.DISABLE
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState.END
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState.HIDDEN
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState.MIDDLE
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState.SELECTABLE
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.DateState.START
import com.jeju.nanaland.util.daterangecalendar.customviews.DateView.OnDateClickListener
import com.jeju.nanaland.util.daterangecalendar.models.CalendarStyleAttributes
import com.jeju.nanaland.util.daterangecalendar.models.CalendarStyleAttributes.DateSelectionMode.FIXED_RANGE
import com.jeju.nanaland.util.daterangecalendar.models.CalendarStyleAttributes.DateSelectionMode.FREE_RANGE
import com.jeju.nanaland.util.daterangecalendar.models.CalendarStyleAttributes.DateSelectionMode.SINGLE
import com.jeju.nanaland.util.daterangecalendar.models.DateTiming
import com.jeju.nanaland.util.daterangecalendar.timepicker.AwesomeTimePickerDialog
import com.jeju.nanaland.util.daterangecalendar.timepicker.AwesomeTimePickerDialog.TimePickerCallback
import com.jeju.nanaland.util.language.customContext
import com.jeju.nanaland.util.resource.getString
import java.util.Calendar

/**
 * Created by archit.shah on 08/09/2017.
 */
internal class DateRangeMonthView : LinearLayout {
    private lateinit var llDaysContainer: LinearLayout
    private lateinit var llTitleWeekContainer: LinearLayout
    private lateinit var currentCalendarMonth: Calendar
    private lateinit var calendarStyleAttr: CalendarStyleAttributes
    private var calendarListener: CalendarListener? = null
    private lateinit var dateRangeCalendarManager: CalendarDateRangeManager
    private val fontScale = resources.configuration.fontScale
    private val dpi = resources.configuration.densityDpi

    private val mOnDateClickListener: OnDateClickListener = object : OnDateClickListener {
        override fun onDateClicked(view: View, selectedDate: Calendar) {
            if (calendarStyleAttr.isEditable) {
                if (calendarStyleAttr.isShouldEnabledTime) {
                    val awesomeTimePickerDialog = AwesomeTimePickerDialog(context,
                        context.getString(R.string.select_time), object : TimePickerCallback {
                            override fun onTimeSelected(hours: Int, mins: Int) {
                                selectedDate[Calendar.HOUR] = hours
                                selectedDate[Calendar.MINUTE] = mins
                                setSelectedDate(selectedDate)
                            }

                            override fun onCancel() {
                                resetAllSelectedViews()
                            }
                        })
                    awesomeTimePickerDialog.showDialog()
                } else {
                    setSelectedDate(selectedDate)
                }
            }
        }
    }

    fun setCalendarListener(calendarListener: CalendarListener?) {
        this.calendarListener = calendarListener
    }

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initView(context)
    }

    /**
     * To initialize child views
     *
     * @param context      - App context
     * @param attributeSet - Attr set
     */
    private fun initView(context: Context) {
        val layoutInflater = LayoutInflater.from(context)
        val mainView = layoutInflater.inflate(R.layout.layout_calendar_month, this, true) as LinearLayout
        llDaysContainer = mainView.findViewById(R.id.llDaysContainer)
//        llDaysContainer.layoutParams.height = 200 * 360 / dpi
        llTitleWeekContainer = mainView.findViewById(R.id.llTitleWeekContainer)
//        mainView.findViewById<CustomTextView>(R.id.tvDayOfWeek1).text =
    }

    private fun setSelectedDate(selectedDate: Calendar) {
        val selectionMode = calendarStyleAttr.dateSelectionMode
        var minSelectedDate = dateRangeCalendarManager.getMinSelectedDate()
        var maxSelectedDate = dateRangeCalendarManager.getMaxSelectedDate()

        when (selectionMode) {
            FREE_RANGE -> {
                if (minSelectedDate != null && maxSelectedDate == null) {
                    maxSelectedDate = selectedDate
                    val startDateKey = getContainerKey(minSelectedDate)
                    val lastDateKey = getContainerKey(maxSelectedDate)
                    if (startDateKey == lastDateKey) {
                        minSelectedDate = maxSelectedDate
                    } else if (startDateKey > lastDateKey) {
                        val temp = minSelectedDate.clone() as Calendar
                        minSelectedDate = maxSelectedDate
                        maxSelectedDate = temp
                    }
                } else if (maxSelectedDate == null) {
                    //This will call one time only
                    minSelectedDate = selectedDate
                } else {
                    minSelectedDate = selectedDate
                    maxSelectedDate = null
                }
            }
            SINGLE -> {
                minSelectedDate = selectedDate
                maxSelectedDate = selectedDate
            }
            FIXED_RANGE -> {
                minSelectedDate = selectedDate
                maxSelectedDate = selectedDate.clone() as Calendar
                maxSelectedDate.add(Calendar.DATE, calendarStyleAttr.fixedDaysSelectionNumber)
            }
        }

        dateRangeCalendarManager.setSelectedDateRange(minSelectedDate, maxSelectedDate)
        drawCalendarForMonth(currentCalendarMonth)
        Log.i(LOG_TAG, "Time: " + selectedDate.time.toString())
        if (maxSelectedDate != null) {
            calendarListener!!.onDateRangeSelected(minSelectedDate, maxSelectedDate)
        } else {
            calendarListener!!.onFirstDateSelected(minSelectedDate)
        }
    }

    /**
     * To draw calendar for the given month. Here calendar object should start from date of 1st.
     *
     * @param calendarStyleAttr        Calendar style attributes
     * @param month                    Month to be drawn
     * @param dateRangeCalendarManager Calendar data manager
     */
    fun drawCalendarForMonth(
        calendarStyleAttr: CalendarStyleAttributes,
        month: Calendar,
        dateRangeCalendarManager: CalendarDateRangeManager,
    ) {
        this.calendarStyleAttr = calendarStyleAttr
        currentCalendarMonth = month.clone() as Calendar
        this.dateRangeCalendarManager = dateRangeCalendarManager
        drawCalendarForMonth(currentCalendarMonth)
    }

    /**
     * To draw calendar for the given month. Here calendar object should start from date of 1st.
     *
     * @param month Calendar month
     */
    private fun drawCalendarForMonth(month: Calendar) {
        setWeekTextAttributes()
        currentCalendarMonth = month.clone() as Calendar
        currentCalendarMonth[Calendar.DATE] = 1
        resetTime(
            currentCalendarMonth,
            DateTiming.NONE
        )
        val weekTitle = customContext.resources.getStringArray(R.array.week_sun_sat)

        //To set week day title as per offset
        for (i in 0 until TOTAL_DAYS_IN_A_WEEK) {
            // 달력 상단의 월화수목금토일
            val textView = llTitleWeekContainer.getChildAt(i) as CustomTextView
            textView.layoutParams.height = 40 * resources.displayMetrics.widthPixels / 360
//            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f / fontScale * resources.displayMetrics.widthPixels / 360)
            textView.textSize = 12f / fontScale * 160 / dpi * resources.displayMetrics.widthPixels / 360
            val weekStr = weekTitle[(i + calendarStyleAttr.weekOffset) % TOTAL_DAYS_IN_A_WEEK]
            textView.text = weekStr
        }
        var startDay = month[Calendar.DAY_OF_WEEK] - calendarStyleAttr.weekOffset

        //To rotate week day according to offset
        if (startDay < 1) {
            startDay += TOTAL_DAYS_IN_A_WEEK
        }
        month.add(Calendar.DATE, -startDay + 1)
        for (i in 0 until llDaysContainer.childCount) {
            // 월화수목금토일 밑의 날짜 한 줄
            val weekRow = llDaysContainer.getChildAt(i) as LinearLayout
            weekRow.layoutParams.height = 50 * resources.displayMetrics.widthPixels / 360
            for (j in 0 until TOTAL_DAYS_IN_A_WEEK) {
                val customDateView = weekRow.getChildAt(j) as CustomDateView
                customDateView.layoutParams.height = 40 * resources.displayMetrics.widthPixels / 360
                customDateView.layoutParams.width = 40 * resources.displayMetrics.widthPixels / 360
                drawDayContainer(customDateView, month)
                month.add(Calendar.DATE, 1)
            }
        }
    }

    /**
     * To draw specific date container according to past date, today, selected or from range.
     *
     * @param customDateView - Date container
     * @param date       - Calendar obj of specific date of the month.
     */
    private fun drawDayContainer(customDateView: CustomDateView, date: Calendar) {
        customDateView.setDateText(date[Calendar.DATE].toString())
        customDateView.setDateStyleAttributes(calendarStyleAttr)
        customDateView.setDateClickListener(mOnDateClickListener)
        calendarStyleAttr.fonts?.let { customDateView.setTypeface(it) }
        val dateState: DateState = if (currentCalendarMonth[Calendar.MONTH] != date[Calendar.MONTH]) {
            HIDDEN
        } else {
            val type = dateRangeCalendarManager.checkDateRange(date)
            if (type === START_DATE) {
                START
            } else if (type === LAST_DATE) {
                END
            } else if (type === START_END_SAME) {
                DateState.START_END_SAME
            } else if (type === IN_SELECTED_RANGE) {
                MIDDLE
            } else {
                if (dateRangeCalendarManager.isSelectableDate(date)) {
                    SELECTABLE
                } else {
                    DISABLE
                }
            }
        }
        customDateView.updateDateBackground(dateState)
        customDateView.tag = getContainerKey(date)
    }

    /**
     * To remove all selection and redraw current calendar
     */
    fun resetAllSelectedViews() {
        dateRangeCalendarManager.resetSelectedDateRange()
        drawCalendarForMonth(currentCalendarMonth)
    }

    /**
     * To apply configs to all the text views
     */
    private fun setWeekTextAttributes() {
        for (i in 0 until llTitleWeekContainer.childCount) {
            val textView = llTitleWeekContainer.getChildAt(i) as CustomTextView
            textView.typeface = calendarStyleAttr.fonts
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, calendarStyleAttr.textSizeWeek)
            textView.setTextColor(calendarStyleAttr.weekColor)
        }
    }

    companion object {
        private val LOG_TAG = DateRangeMonthView::class.java.simpleName
        private const val TOTAL_DAYS_IN_A_WEEK = 7
    }
}
