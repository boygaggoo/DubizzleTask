package com.android.dubizzle.mvi.util

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object  DateUtil {
    @SuppressLint("DefaultLocale", "SimpleDateFormat")
    fun timeSince(createdByDate: String?): String {

        //"2019-02-23 07:56:26.686128"
        val originalFormat =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US)
        originalFormat.timeZone = TimeZone.getDefault()
        var creationDate: Date? = null

        try {
            creationDate = originalFormat.parse(createdByDate!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val seconds =
            (System.currentTimeMillis() - creationDate!!.time) / 1000


        // return "now" if less than a minute has elapsed
        if (seconds < 60) return "Now"

        // less than an pHour (ex: 12m)
        val minutesSince = seconds / 60
        if (minutesSince < 60) {
            return if (minutesSince == 1L) {
                String.format("%d", minutesSince) + " min ago"
            } else {
                String.format("%d", minutesSince) + " mins ago"
            }
        }
        // less than a day (ex: 17h)
        val hoursSince = minutesSince / 60
        if (hoursSince < 24) {
            return if (hoursSince == 1L) {
                String.format("%d", hoursSince) + " hour ago"
            } else {
                String.format("%d", hoursSince) + " hours ago"
            }
        }
        // less than a week (ex: 5d)
        val daysSince = hoursSince / 24
        return if (daysSince < 7) {
            if (daysSince == 1L) {
                String.format("%d", daysSince) + " day ago"
            } else {
                String.format("%d", daysSince) + " days ago"
            }
        } else SimpleDateFormat("dd MMM yyyy").format(creationDate)
    }
}