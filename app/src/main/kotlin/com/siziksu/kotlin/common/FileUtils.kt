package com.siziksu.kotlin.common

import android.content.Context
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

object FileUtils {

    private val CHARSET_UTF_8 = "UTF-8"
    private var context: Context? = null

    fun init(context: Context) {
        this.context = context
    }

    fun getStringResource(string: String): String {
        val s = context?.resources?.getIdentifier(string, "string", context?.packageName)
        return context?.getString(s!!) ?: ""
    }

    fun getStringFromFile(file: String): String {
        val manager = context?.assets
        val stream = manager?.open(file)
        val string = convertStreamToString(stream)
        stream?.close()
        return string
    }

    private fun convertStreamToString(stream: InputStream?): String {
        val reader = BufferedReader(InputStreamReader(stream, CHARSET_UTF_8))
        val builder = StringBuilder()
        var line = reader.readLine()
        while (line != null) {
            builder.append(line).append("\n")
            line = reader.readLine()
        }
        return builder.toString()
    }
}
