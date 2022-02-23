package de.orome.touren7.helper

import android.os.Environment
import android.text.Editable
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Helper {

    fun returnActualDate(): String
    {
        val c = Calendar.getInstance()
        val year= c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val datum: String = day.toString() + "." + month.toString() + "." + year.toString()

        return datum
    }

    fun modifyMinuteHourDayMonth(hh: Int): String
    {
        var std: String = ""
        hh.let {
            if (hh < 10) {
                std = "0$hh"
            } else {
                std = hh.toString()
            }
        }
        return std
    }

     fun setUpDirectory() {
        var dbDirectory = Environment.getExternalStorageDirectory().absolutePath + "/data/touren/databases/"
        var dbDirFile = File(dbDirectory)
        if (!dbDirFile.exists()) {
            dbDirFile.mkdirs()
        }
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    fun convertDateStringToInt(dateString: String): Int {
        var dateInt =0
        val dummy = dateString.split(".")
        if (dummy.size == 3){
            val datString = ""+ dummy[2]+dummy[1]+dummy[0]
            dateInt = datString.toInt()
        }

        return dateInt
    }
}