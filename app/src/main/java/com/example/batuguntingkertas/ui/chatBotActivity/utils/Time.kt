package com.example.batuguntingkertas.ui.chatBotActivity.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object Time {
    fun timeStamp() : String {
        val timeStamp = Timestamp(System.currentTimeMillis())
        val formatTanggal = SimpleDateFormat("HH:MM")
        val time = formatTanggal.format(Date(timeStamp.time))

        return time.toString()
    }
}