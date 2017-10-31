package com.haroldolivieri.badasseslist.feature.main

import com.haroldolivieri.badasseslist.domain.BadAss


interface MainView {
    fun showBadAssData(badAsses : List<BadAss>)
    fun onError(errorMessage: String?)
}