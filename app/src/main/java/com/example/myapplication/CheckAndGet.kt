package com.example.myapplication

import android.annotation.SuppressLint
import java.lang.StringBuilder

class CheckAndGet {

    fun checkTime(input: String): Boolean {
        val check = Regex("""(\d+m)(\d+s)""")
        return check.matches(input)
    }

    fun sumTime(timeOne: String, timeTwo: String): String {
        val totalseconds = convertToSeconds(timeOne) + convertToSeconds(timeTwo)
        return convertToFormat(totalseconds)
    }

    fun difTime(timeOne: String, timeTwo: String): String {
        val totalseconds = convertToSeconds(timeOne) - convertToSeconds(timeTwo)
        return convertToFormat(totalseconds)
    }

    @SuppressLint("SuspiciousIndentation")
    private fun convertToSeconds(time: String): Int {
        var totalSeconds = 0
        val regex = """(\d+)([hms])""".toRegex()
        val matches = regex.findAll(time)
        for (match in matches) {
            val value = match.groupValues[1].toInt()
                    when (match.groupValues[2]) {
                        "h" -> totalSeconds += value * 3600
                        "m" -> totalSeconds += value * 60
                        "s" -> totalSeconds += value
                    }
        }
        return totalSeconds
    }

    private fun convertToFormat(totalseconds: Int): String {
        val hours = totalseconds / 3600
        val minutes = (totalseconds % 3600) / 60
        val seconds = totalseconds % 60
        val result = StringBuilder()

        if (hours > 0) result.append("${hours}h")
        if (minutes > 0) result.append("${minutes}m")
        if (seconds > 0) result.append("${seconds}s")

        return result.toString().ifEmpty { "0s" }
    }
}