package net.hdhuu.splee.utils

import android.annotation.SuppressLint

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Huu Hoang on 2/19/19.
 */
class TImeHelper {
    @SuppressLint("SimpleDateFormat")
    fun createdAt(times: Long): String {
        val date = Date(times)
        val formatter = SimpleDateFormat("HH:mm:ss")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
       return "Created at ${formatter.format(date)}"
    }


}
