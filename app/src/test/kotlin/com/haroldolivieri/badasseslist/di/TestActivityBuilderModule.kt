package com.haroldolivieri.badasseslist.di

import com.haroldolivieri.badasseslist.feature.details.DetailsActivity
import com.haroldolivieri.badasseslist.feature.details.DetailsActivityModule
import com.haroldolivieri.badasseslist.feature.details.TestDetailsActivityModule
import com.haroldolivieri.badasseslist.feature.main.MainActivity
import com.haroldolivieri.badasseslist.feature.main.TestMainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class TestActivityBuilderModule {
    @ContributesAndroidInjector(modules = arrayOf(TestMainActivityModule::class))
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(TestDetailsActivityModule::class))
    internal abstract fun bindDetailsActivity(): DetailsActivity
}