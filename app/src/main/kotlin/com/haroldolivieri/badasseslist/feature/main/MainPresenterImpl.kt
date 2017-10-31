package com.haroldolivieri.badasseslist.feature.main

import android.support.annotation.VisibleForTesting
import com.haroldolivieri.badasseslist.repository.BadAssRepository
import javax.inject.Inject


class MainPresenterImpl
@Inject constructor(private val mainView: MainView,
                    private val badAssRepository: BadAssRepository) : MainPresenter {

    override fun fetchData() {
        badAssRepository
                .fetch()
                .toList()
                .subscribe({ mainView.showBadAssData(it) },
                        { mainView.onError(it.message) })
    }
}