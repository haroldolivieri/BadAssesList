package com.haroldolivieri.badasseslist.feature.main

import android.support.annotation.VisibleForTesting
import com.haroldolivieri.badasseslist.feature.BasePresenter
import com.haroldolivieri.badasseslist.repository.BadAssRepository

interface MainPresenter : BasePresenter {
    fun fetchData()
}