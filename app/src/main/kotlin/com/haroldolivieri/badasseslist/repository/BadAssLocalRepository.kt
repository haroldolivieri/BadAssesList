package com.haroldolivieri.badasseslist.repository

import com.google.gson.GsonBuilder
import com.haroldolivieri.badasseslist.domain.BadAss
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import javax.inject.Inject


class BadAssLocalRepository @Inject constructor(private val inputStream: InputStream)
    : BadAssRepository {

    override fun fetch(): Observable<BadAss> = Observable
            .fromIterable(readDataLocally()?.let { toSet(it) })

    private fun toSet(jsonStr: String): Set<BadAss> {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create()
        return gson.fromJson(jsonStr, object : TypeToken<Set<BadAss>>() {}.type)
    }

    private fun readDataLocally(): String? {
        val size = inputStream.available()
        val buffer = ByteArray(size)

        inputStream.read(buffer)
        inputStream.close()

        return String(buffer)
    }
}