package com.haroldolivieri.badasseslist

import com.haroldolivieri.badasseslist.di.ApplicationComponent
import com.haroldolivieri.badasseslist.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

open class BadAssesApplication : DaggerApplication() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build()

        applicationComponent.inject(this)
        return applicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        calligraphyConfig()
    }

    private fun calligraphyConfig() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.bariol_regular))
                .setFontAttrId(R.attr.fontPath)
                .build())
    }
}