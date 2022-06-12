package com.codesquad.starbucks.ui.common

import android.os.Build
import org.joda.time.DateTimeZone
import org.joda.time.Seconds
import org.joda.time.format.DateTimeFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


const val SECONDS_OF_DAY = 60 * 60 * 24

fun getTimeDiff(dateTimeInfo: String): Boolean {
    val seconds = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val nowDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
        val dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val actionDateTime = LocalDateTime.parse(dateTimeInfo, dataFormatter)
        Duration.between(actionDateTime, nowDateTime).seconds.toInt()
    } else {
        val dataFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
        val nowDateTime = org.joda.time.LocalDateTime.now(DateTimeZone.forID("Asia/Seoul"))
        val actionDateTime = org.joda.time.LocalDateTime.parse(dateTimeInfo, dataFormatter)
        Seconds.secondsBetween(actionDateTime, nowDateTime).seconds
    }
    return secondsInOneDayDuration(seconds)
}

fun getNowDateTime(): String {
    val nowDateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(dataFormatter)
    } else {
        org.joda.time.LocalDateTime.now(DateTimeZone.forID("Asia/Seoul"))
    }
    return nowDateTime.toString()
}

fun getNowHour(): String {
    val nowDateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime.now(ZoneId.of("Asia/Seoul")).hour
    } else {
        org.joda.time.LocalDateTime.now(DateTimeZone.forID("Asia/Seoul")).hourOfDay
    }
    return if (nowDateTime > 12) {
        "오후 ${nowDateTime - 12}시 기준"
    } else {
        "오전 ${nowDateTime}시 기준"
    }

}

fun secondsInOneDayDuration(seconds: Int): Boolean {
    return when {
        seconds > SECONDS_OF_DAY -> false
        else -> true
    }
}