package com.haroldolivieri.badasseslist

import com.haroldolivieri.badasseslist.di.DaggerTestApplicationComponent
import com.haroldolivieri.badasseslist.di.TestApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication



class TestBadAssesApplication : BadAssesApplication() {

    companion object {
        lateinit var testApplicationComponent: TestApplicationComponent
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        testApplicationComponent = DaggerTestApplicationComponent.builder()
                .application(this)
                .build()

        testApplicationComponent.inject(this)
        return testApplicationComponent
    }

}