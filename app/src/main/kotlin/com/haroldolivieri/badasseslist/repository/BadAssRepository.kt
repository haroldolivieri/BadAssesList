package com.haroldolivieri.badasseslist.repository

import com.haroldolivieri.badasseslist.domain.BadAss
import io.reactivex.Observable


interface BadAssRepository {
    fun fetch() : Observable<BadAss>
}