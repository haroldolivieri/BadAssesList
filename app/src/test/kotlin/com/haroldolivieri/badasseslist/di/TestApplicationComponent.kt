package com.haroldolivieri.badasseslist.di

import android.app.Application
import com.haroldolivieri.badasseslist.TestBadAssesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        TestActivityBuilderModule::class))
interface TestApplicationComponent : AndroidInjector<TestBadAssesApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): TestApplicationComponent
    }
}