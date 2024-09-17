package com.app.grckikino.utils

import com.app.grckikino.utils.KeysAndConstants.HISTORY_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getHistoryTimeString(timestamp: Long): String {
    val dateFormat = SimpleDateFormat(HISTORY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}